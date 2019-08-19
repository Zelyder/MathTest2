package com.zelyder.mathtest.domain.models

import androidx.room.ColumnInfo

data class FormulaModel (
    @ColumnInfo(name = "id_formula")
    val id: Int,
    @ColumnInfo(name = "id_subcategory")
    val subcategoryId: Int,
    val name: String,
    val formula: String,
    val img: Int
)