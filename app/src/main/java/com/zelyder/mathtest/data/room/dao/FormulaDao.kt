package com.zelyder.mathtest.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zelyder.mathtest.data.room.entity.Formula
import com.zelyder.mathtest.domain.models.FormulaModel

@Dao
interface FormulaDao {

    @Query("SELECT formulas.id_formula, formulas.id_subcategory, languages.eng_name AS name, formulas.formula," +
            "formulas.id_img AS img FROM formulas, languages WHERE formulas.id_name == languages.id " +
            "AND formulas.id_subcategory == :idSubcategory")
    fun getEngFormulas(idSubcategory: Int): LiveData<List<FormulaModel>>

    @Query("SELECT formulas.id_formula, formulas.id_subcategory, languages.rus_name AS name, formulas.formula," +
            "formulas.id_img AS img FROM formulas, languages WHERE formulas.id_name == languages.id " +
            "AND formulas.id_subcategory == :idSubcategory")
    fun getRusFormulas(idSubcategory: Int): LiveData<List<FormulaModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(formula: Formula)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(formulas: List<Formula>)
}