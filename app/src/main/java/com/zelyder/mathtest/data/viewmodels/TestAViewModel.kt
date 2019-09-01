package com.zelyder.mathtest.data.viewmodels

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.room.AppDatabase
import com.zelyder.mathtest.domain.models.FormulaModel
import com.zelyder.mathtest.domain.repository.FormulaRepository
import com.zelyder.mathtest.help.FormulaUtilities
import com.zelyder.mathtest.help.Utilities
import com.zelyder.mathtest.interfaces.DialogControl
import com.zelyder.mathtest.interfaces.KeyboardOutput
import java.util.*
import kotlin.collections.ArrayList

class TestAViewModel(app: Application) : AndroidViewModel(app), KeyboardOutput {

    private val _formulaText = MutableLiveData<String>()
    private val _keys = MutableLiveData<Array<String>>()
    private val _name = MutableLiveData<String>()
    private val _formulasList = MutableLiveData<ArrayList<FormulaModel>>()

    private val formulaRepository: FormulaRepository
    private val pref: SharedPreferences


    val formulaText: LiveData<String> = _formulaText
    val keys: LiveData<Array<String>> = _keys
    val name: LiveData<String> = _name
    val keyboardOutput: KeyboardOutput = this
    lateinit var dialogControl: DialogControl

    private var formulaId: Int = 0
    private var startCountTry: Int = 3
    private var countTry: Int = startCountTry
    private var correctAnswers: Int = 0
    private var countFormulas: Int = 0

    private val animImgIn: Animation
    private  val animImgOut: Animation
    private  var isImgBig = false



    init {
        val formulaDao =
            AppDatabase.getDatabase(app.applicationContext, viewModelScope).formulaDao()

        formulaRepository = FormulaRepository(formulaDao)
        pref = PreferenceManager.getDefaultSharedPreferences(app.applicationContext)

        _formulaText.value = ""
        _keys.value = arrayOf()

        animImgIn = AnimationUtils.loadAnimation(app.applicationContext, R.anim.scale_in_img)
        animImgOut = AnimationUtils.loadAnimation(app.applicationContext, R.anim.scale_out_img)
    }

    override fun deleteChar() {
        _formulaText.value = "$$${FormulaUtilities().delCharQRight(_formulaText.value!!)}$$"
    }

    override fun insertChar(char: String) {
        _formulaText.value = "$$${FormulaUtilities().addCharQ(_formulaText.value!!, char)}$$"
    }

    fun changeImgState(view: View){
        isImgBig = if(!isImgBig){
            view.startAnimation(animImgIn)
//            view.scaleX = 2.0f
//            view.scaleY = 2.0f
            true
        }else{
            view.startAnimation(animImgOut)
//            view.scaleX = 1.0f
//            view.scaleY = 1.0f
            false
        }
    }

    override fun checkFormula() {
        if(_formulaText.value?.isNotEmpty()!!) {
            if (formulaText.value?.equals(_formulasList.value?.get(formulaId)?.formula)!!) {
                Toast.makeText(getApplication(), "Правильно", Toast.LENGTH_SHORT).show()
                if (countTry > 0) {
                    correctAnswers++
                }
                nextFormula()
            } else {
                countTry--
                if (countTry > 0) {
                    Toast.makeText(
                        getApplication(),
                        "Неправильно осталось попыток: $countTry",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    _formulasList.value?.get(formulaId)?.formula?.let {
                        dialogControl.createCorrectDialog(it)
                    }
                }
            }
        }else{
            nextFormula()
        }

    }

    fun setKeys(keys: Array<String>) {
        _keys.value = keys
    }


    private fun setFormula(name: String, text: String) {
        _name.value = name
        _formulaText.value = FormulaUtilities().toUnknownRight(text)
    }

    fun setFormulas(SubcategoryId: Int, lifecycleOwner: LifecycleOwner) {
        val formulasDataList: LiveData<List<FormulaModel>> = when(pref.getString("pref_lang","en")!!){
            "en" -> formulaRepository.getFormulasEng(SubcategoryId)
            "ru" -> formulaRepository.getFormulasRus(SubcategoryId)
            else -> formulaRepository.getFormulasEng(SubcategoryId)
        }
        formulasDataList.observe(lifecycleOwner, Observer {
            it?.let {
                _formulasList.value = ArrayList(it)
                countFormulas = it.size
                //TODO: вынести отсюда(проблемма с потоками)
                getRandomFormula()
            }
        })

    }

    private fun getRandomFormula() {
        with(_formulasList.value) {
            this?.let {
                formulaId = Random().nextInt(it.size)
                val formula = it[formulaId]
                setFormula(formula.name, formula.formula)
            }
        }
        countTry = startCountTry
    }

    fun nextFormula() {
        if(!_formulasList.value.isNullOrEmpty()) {
            _formulasList.value?.removeAt(formulaId)
        }
        if(_formulasList.value.isNullOrEmpty()){
            dialogControl.createFinalDialog(correctAnswers, countFormulas - correctAnswers)
        }else {
            getRandomFormula()
        }
    }
}