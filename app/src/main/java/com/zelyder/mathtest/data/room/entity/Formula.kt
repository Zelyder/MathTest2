package com.zelyder.mathtest.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formulas")
data class Formula(
    @PrimaryKey @ColumnInfo(name = "id_formula") val idFormula: Int,
    @ColumnInfo(name = "id_subcategory") val idSubcategory: Int,
    @ColumnInfo(name = "id_name") val idName: String,
    @ColumnInfo(name = "formula") val formula: String,
    @ColumnInfo(name = "id_img") var idImg: Int
)