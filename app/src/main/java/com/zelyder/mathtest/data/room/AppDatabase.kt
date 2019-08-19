package com.zelyder.mathtest.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.zelyder.mathtest.data.room.dao.CategoryDao
import com.zelyder.mathtest.data.room.dao.FormulaDao
import com.zelyder.mathtest.data.room.dao.LanguageDao
import com.zelyder.mathtest.data.room.dao.SubcategoryDao
import com.zelyder.mathtest.data.room.entity.Category
import com.zelyder.mathtest.data.room.entity.Formula
import com.zelyder.mathtest.data.room.entity.Language
import com.zelyder.mathtest.data.room.entity.Subcategory
import com.zelyder.mathtest.help.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities =  [Formula::class, Language::class, Category::class, Subcategory::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun formulaDao(): FormulaDao
    abstract fun subcategoryDao(): SubcategoryDao
    abstract fun languageDao(): LanguageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(DatabaseCallback(context.applicationContext, coroutineScope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
    private class DatabaseCallback(private val context: Context, private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                scope.launch(Dispatchers.IO) {
                   populateDatabase()
                }
            }
        }

        suspend fun populateDatabase(){

            context.assets.open(LANGUAGE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val languageType = object : TypeToken<List<Language>>() {}.type
                    val languageList: List<Language> = Gson().fromJson(jsonReader, languageType)
                    INSTANCE?.languageDao()?.insertAll(languageList)
                }
            }

            context.assets.open(CATETGORY_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val categoryType = object : TypeToken<List<Category>>() {}.type
                    val categoryList: List<Category> = Gson().fromJson(jsonReader, categoryType)
                    INSTANCE?.categoryDao()?.insertAll(categoryList)
                }
            }

            context.assets.open(SUBCATETGORY_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val subcategoryType = object : TypeToken<List<Subcategory>>() {}.type
                    val subcategoryList: List<Subcategory> = Gson().fromJson(jsonReader, subcategoryType)
                    INSTANCE?.subcategoryDao()?.insertAll(subcategoryList)
                }
            }

            context.assets.open(FORMULA_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val formulaType = object : TypeToken<List<Formula>>() {}.type
                    val formulaList: List<Formula> = Gson().fromJson(jsonReader, formulaType)
                    INSTANCE?.formulaDao()?.insertAll(formulaList)
                }
            }

        }
    }
}