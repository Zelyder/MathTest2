package com.zelyder.mathtest.domain.repository

import androidx.annotation.WorkerThread
import com.zelyder.mathtest.data.room.dao.SubcategoryDao

class SubcategoryRepository(private val subcategoryDao: SubcategoryDao) {

    @WorkerThread
    fun getSubcategoriesEng(idCategory: Int) = subcategoryDao.getEngSubcategories(idCategory)

    @WorkerThread
    fun getSubcategoriesRus(idCategory: Int) = subcategoryDao.getRusSubcategories(idCategory)
}