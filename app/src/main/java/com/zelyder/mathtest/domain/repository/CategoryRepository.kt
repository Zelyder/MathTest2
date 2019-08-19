package com.zelyder.mathtest.domain.repository

import androidx.annotation.WorkerThread
import com.zelyder.mathtest.data.room.dao.CategoryDao

class CategoryRepository(private val categoryDao: CategoryDao) {

    @WorkerThread
    fun getCategoriesEng() = categoryDao.getEngCategories()

    @WorkerThread
    fun getCategoriesRus() = categoryDao.getRusCategories()
}