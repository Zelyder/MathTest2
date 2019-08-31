package com.zelyder.mathtest.data.viewmodels

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zelyder.mathtest.R
import com.zelyder.mathtest.data.room.AppDatabase
import com.zelyder.mathtest.domain.models.CategoryModel
import com.zelyder.mathtest.domain.models.SubcategoryModel
import com.zelyder.mathtest.domain.repository.CategoryRepository
import com.zelyder.mathtest.domain.repository.SubcategoryRepository

class MainViewModel(app: Application): AndroidViewModel(app) {

    private val categoryRepository: CategoryRepository
    private val subcategoryRepository: SubcategoryRepository
    private val pref: SharedPreferences

    val categories:LiveData<List<CategoryModel>>

    init {

        val categoryDao = AppDatabase.getDatabase(app, viewModelScope).categoryDao()
        val subcategoryDao = AppDatabase.getDatabase(app, viewModelScope).subcategoryDao()

        categoryRepository = CategoryRepository(categoryDao)
        subcategoryRepository = SubcategoryRepository(subcategoryDao)

        pref = PreferenceManager.getDefaultSharedPreferences(app.applicationContext)

        categories = when(pref.getString("pref_lang","en")!!){
            "en" -> categoryRepository.getCategoriesEng()
            "ru" -> categoryRepository.getCategoriesRus()
            else -> categoryRepository.getCategoriesEng()
        }

    }

    fun getSubcategories(categoryId: Int): LiveData<List<SubcategoryModel>> {
        return when(pref.getString("pref_lang","en")!!){
            "en" -> subcategoryRepository.getSubcategoriesEng(categoryId)
            "ru" -> subcategoryRepository.getSubcategoriesRus(categoryId)
            else -> subcategoryRepository.getSubcategoriesEng(categoryId)
        }
    }

}