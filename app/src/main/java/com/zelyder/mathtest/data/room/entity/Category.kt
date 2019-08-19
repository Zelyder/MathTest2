package com.zelyder.mathtest.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_category") val  idCategory: Int = 0,
    @ColumnInfo(name = "id_name") val idName: String,
    @ColumnInfo(name = "img") val img: Int
)
