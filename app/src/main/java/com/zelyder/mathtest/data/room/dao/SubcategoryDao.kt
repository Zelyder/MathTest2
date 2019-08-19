package com.zelyder.mathtest.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zelyder.mathtest.data.room.entity.Subcategory
import com.zelyder.mathtest.domain.models.SubcategoryModel

@Dao
interface SubcategoryDao {

    @Query("SELECT subcategories.id_subcategory, languages.eng_name AS name FROM subcategories, languages" +
            " WHERE subcategories.id_name == languages.id AND subcategories.id_category == :idCategory")
    fun getEngSubcategories(idCategory: Int): LiveData<List<SubcategoryModel>>

    @Query("SELECT subcategories.id_subcategory, languages.rus_name AS name FROM subcategories, languages" +
            " WHERE subcategories.id_name == languages.id AND subcategories.id_category == :idCategory")
    fun getRusSubcategories(idCategory: Int): LiveData<List<SubcategoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subcategory: Subcategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subcategories: List<Subcategory>)
}