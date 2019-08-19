package com.zelyder.mathtest.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "languages")
data class Language (
    @PrimaryKey @ColumnInfo(name = "id") val idName: String,
    @ColumnInfo(name = "eng_name") val engName: String,
    @ColumnInfo(name = "rus_name") val rusName: String
)