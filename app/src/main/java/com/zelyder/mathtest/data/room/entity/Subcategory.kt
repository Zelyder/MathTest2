package com.zelyder.mathtest.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subcategories")
data class Subcategory (
    @PrimaryKey @ColumnInfo(name = "id_subcategory") val idSubcategory: Int,
    @ColumnInfo(name = "id_category") val idCategory: Int,
    @ColumnInfo(name = "id_name") val idName: String
)