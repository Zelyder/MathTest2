package com.zelyder.mathtest.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zelyder.mathtest.data.room.entity.Category
import com.zelyder.mathtest.domain.models.CategoryModel

@Dao
interface CategoryDao {

    @Query("SELECT categories.id_category, languages.eng_name AS name, categories.img FROM categories, languages" +
            " WHERE categories.id_name == languages.id")
    fun getEngCategories():LiveData<List<CategoryModel>>

    @Query("SELECT categories.id_category, languages.rus_name AS name, categories.img FROM categories, languages" +
            " WHERE categories.id_name == languages.id")
    fun getRusCategories():LiveData<List<CategoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)
}