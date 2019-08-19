package com.zelyder.mathtest.domain.models

import androidx.room.ColumnInfo

data class CategoryModel (
    @ColumnInfo(name = "id_category")
    val id: Int,
    val name: String,
    val img: Int
)