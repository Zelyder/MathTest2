package com.zelyder.mathtest.data.viewmodels

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zelyder.mathtest.data.room.AppDatabase
import com.zelyder.mathtest.domain.models.FormulaModel
import com.zelyder.mathtest.domain.repository.FormulaRepository

class FormulasListViewModel(app: Application): AndroidViewModel(app) {

    private val formulaRepository: FormulaRepository
    private val pref: SharedPreferences

    init {

        val formulaDao = AppDatabase.getDatabase(app.applicationContext, viewModelScope).formulaDao()

        pref = PreferenceManager.getDefaultSharedPreferences(app.applicationContext)
        formulaRepository = FormulaRepository(formulaDao)
    }

    fun getFormulas(SubcategoryId: Int): LiveData<List<FormulaModel>> =
        when(pref.getString("pref_lang","en")!!){
            "en" -> formulaRepository.getFormulasEng(SubcategoryId)
            "ru" -> formulaRepository.getFormulasRus(SubcategoryId)
            else -> formulaRepository.getFormulasEng(SubcategoryId)
        }

}