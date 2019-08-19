package com.zelyder.mathtest.domain.repository

import androidx.annotation.WorkerThread
import com.zelyder.mathtest.data.room.dao.FormulaDao

class FormulaRepository(private val formulaDao: FormulaDao) {

    @WorkerThread
    fun getFormulasEng(idSubcategory: Int) = formulaDao.getEngFormulas(idSubcategory)

    @WorkerThread
    fun getFormulasRus(idSubcategory: Int) = formulaDao.getRusFormulas(idSubcategory)
}