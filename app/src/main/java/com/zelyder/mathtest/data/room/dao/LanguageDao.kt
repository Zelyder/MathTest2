package com.zelyder.mathtest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.zelyder.mathtest.data.room.entity.Language

@Dao
interface LanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(language: Language)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(languages: List<Language>)
}