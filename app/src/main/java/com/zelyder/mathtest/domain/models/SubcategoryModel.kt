package com.zelyder.mathtest.domain.models

import androidx.room.ColumnInfo

data class SubcategoryModel (
    @ColumnInfo(name =  "id_subcategory")
    val id: Int,
    val name: String
)