package com.zelyder.mathtest.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zelyder.mathtest.help.Utilities
import com.zelyder.mathtest.interfaces.KeyboardOutput

class TestAViewModel: ViewModel(), KeyboardOutput {

    private val _formulaText = MutableLiveData<String>()
    private val _keys = MutableLiveData<Array<String>>()

    val formulaText: LiveData<String> = _formulaText
    val keys: LiveData<Array<String>> = _keys
    val keyboardOutput: KeyboardOutput = this

    init {
        _formulaText.value = "``"
        _keys.value = arrayOf("sin", "a", "+", "-", "b", "2", "3", "c", "L", "G")
    }

    override fun deleteChar() {
        _formulaText.value = "`${Utilities().delChar(_formulaText.value)}`"
    }

    override fun insertChar(char: String) {
       _formulaText.value = "`${Utilities().clearString(_formulaText.value)}$char`"
    }

    override fun checkFormula() {

    }


    fun setKeys (keys: Array<String>){
        _keys.value = keys
    }


}