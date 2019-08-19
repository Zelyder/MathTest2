package com.zelyder.mathtest.data.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zelyder.mathtest.data.room.AppDatabase
import com.zelyder.mathtest.domain.models.CategoryModel
import com.zelyder.mathtest.domain.models.SubcategoryModel
import com.zelyder.mathtest.domain.repository.CategoryRepository
import com.zelyder.mathtest.domain.repository.SubcategoryRepository

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val categoryRepository: CategoryRepository
    private val subcategoryRepository: SubcategoryRepository

    val categories:LiveData<List<CategoryModel>>

    init {

        val categoryDao = AppDatabase.getDatabase(app, viewModelScope).categoryDao()
        val subcategoryDao = AppDatabase.getDatabase(app, viewModelScope).subcategoryDao()

        categoryRepository = CategoryRepository(categoryDao)
        subcategoryRepository = SubcategoryRepository(subcategoryDao)


        categories = categoryRepository.getCategoriesEng()
    }

    fun getSubcategories(categoryId: Int): LiveData<List<SubcategoryModel>> {
        return subcategoryRepository.getSubcategoriesEng(categoryId)
    }

}