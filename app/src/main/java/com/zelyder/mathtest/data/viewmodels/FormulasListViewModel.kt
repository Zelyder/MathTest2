package com.zelyder.mathtest.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zelyder.mathtest.data.room.AppDatabase
import com.zelyder.mathtest.domain.models.FormulaModel
import com.zelyder.mathtest.domain.repository.FormulaRepository

class FormulasListViewModel(app: Application): AndroidViewModel(app) {

    private val formulaRepository: FormulaRepository

    init {

        val formulaDao = AppDatabase.getDatabase(app.applicationContext, viewModelScope).formulaDao()

        formulaRepository = FormulaRepository(formulaDao)
    }

    fun getFormulas(SubcategoryId: Int): LiveData<List<FormulaModel>> = formulaRepository.getFormulasEng(SubcategoryId)

}