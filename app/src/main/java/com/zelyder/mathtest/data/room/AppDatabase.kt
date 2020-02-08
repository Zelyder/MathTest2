package com.zelyder.mathtest.data.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.zelyder.mathtest.R
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

//            Log.d("LOL", "img tri h ${R.drawable.ic_triangle_height}")
//                Log.d("LOL", "img tri angel ${R.drawable.ic_triangle_angle}")

            context.assets.open(CATETGORY_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val categoryType = object : TypeToken<List<Category>>() {}.type
                    val categoryList: List<Category> = Gson().fromJson(jsonReader, categoryType)
                    categoryList[0].img = R.drawable.ic_nav_algebra
                    categoryList[1].img = R.drawable.ic_nav_geometry
                    categoryList[2].img = R.drawable.ic_pyramid
                    categoryList[3].img = R.drawable.ic_nav_trigonometry
                    categoryList[4].img = R.drawable.ic_nav_analytic_geometry
                    categoryList[5].img = R.drawable.ic_nav_derivative
                    categoryList[6].img = R.drawable.ic_nav_integration
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
                    formulaList[44].idImg = R.drawable.ic_triangle_area_height
                    formulaList[45].idImg = R.drawable.ic_triangle_area_angle
                    formulaList[46].idImg = R.drawable.ic_triangle_geron
                    formulaList[47].idImg = R.drawable.ic_triangle_geron
                    formulaList[48].idImg = R.drawable.ic_triangle_in_circle
                    formulaList[49].idImg = R.drawable.ic_triangle_out_circle
                    formulaList[50].idImg = R.drawable.ic_triangle_median
                    formulaList[51].idImg = R.drawable.ic_triangle_bisector
                    formulaList[52].idImg = R.drawable.ic_triangle_bisector
                    formulaList[53].idImg = R.drawable.ic_triangle_height
                    formulaList[54].idImg = R.drawable.ic_triangle_exterior_angle
                    formulaList[55].idImg = R.drawable.ic_triangle_middle_line
                    formulaList[56].idImg = R.drawable.ic_triangle_cos
                    formulaList[57].idImg = R.drawable.ic_triangle_sin
                    formulaList[58].idImg = R.drawable.ic_right_triangle_pif
                    formulaList[59].idImg = R.drawable.ic_right_triangle_height1
                    formulaList[60].idImg = R.drawable.ic_right_triangle_height2
                    formulaList[61].idImg = R.drawable.ic_right_triangle_circle_in
                    formulaList[62].idImg = R.drawable.ic_right_triangle_circle_out
                    formulaList[63].idImg = R.drawable.ic_right_triangle_trig
                    formulaList[64].idImg = R.drawable.ic_right_triangle_trig
                    formulaList[65].idImg = R.drawable.ic_right_triangle_trig
                    formulaList[66].idImg = R.drawable.ic_equilateral_triangle_area
                    formulaList[67].idImg = R.drawable.ic_equilateral_triangle_height
                    formulaList[68].idImg = R.drawable.ic_equilateral_triangle_circle_in
                    formulaList[69].idImg = R.drawable.ic_equilateral_triangle_circle_out
                    formulaList[70].idImg = R.drawable.ic_parallelogram_area_height
                    formulaList[71].idImg = R.drawable.ic_parallelogram_area_angle
                    formulaList[72].idImg = R.drawable.ic_parallelogram_area_dio
                    formulaList[73].idImg = R.drawable.ic_parallelogram_sum_dio
                    formulaList[74].idImg = R.drawable.ic_rectangle_area
                    formulaList[75].idImg = R.drawable.ic_rectangle_area
                    formulaList[76].idImg = R.drawable.ic_rectangle_circle_out
                    formulaList[77].idImg = R.drawable.ic_square_square
                    formulaList[78].idImg = R.drawable.ic_square_square
                    formulaList[79].idImg = R.drawable.ic_square_circle_in
                    formulaList[80].idImg = R.drawable.ic_square_circle_out
                    formulaList[81].idImg = R.drawable.ic_rhombus_area
                    formulaList[82].idImg = R.drawable.ic_rhombus_area
                    formulaList[83].idImg = R.drawable.ic_rhombus_area
                    formulaList[84].idImg = R.drawable.ic_rhombus_area
                    formulaList[85].idImg = R.drawable.ic_rhombus_height
                    formulaList[86].idImg = R.drawable.ic_rhombus_touch_point
                    formulaList[87].idImg = R.drawable.ic_rhombus_touch_point
                    formulaList[88].idImg = R.drawable.ic_rhombus_touch_point
                    formulaList[89].idImg = R.drawable.ic_trapezoid_area_height
                    formulaList[90].idImg = R.drawable.ic_trapezoid_area_diagonals
                    formulaList[91].idImg = R.drawable.ic_trapezoid_middle_line
                    formulaList[92].idImg = R.drawable.ic_trapezoid_angles
                    formulaList[93].idImg = R.drawable.ic_trapezoid_attitude
                    formulaList[94].idImg = R.drawable.ic_trapezoid_pocket
                    formulaList[95].idImg = R.drawable.ic_trapezoid_pocket
                    formulaList[96].idImg = R.drawable.ic_arbitrary_quadrilateral_area1
                    formulaList[97].idImg = R.drawable.ic_arbitrary_quadrilateral_circle_in
                    formulaList[98].idImg = R.drawable.ic_arbitrary_quadrilateral_circle_in
                    formulaList[99].idImg = R.drawable.ic_arbitrary_quadrilateral_circle_in
                    formulaList[100].idImg = R.drawable.ic_arbitrary_quadrilateral_circle_out
                    formulaList[101].idImg = R.drawable.ic_arbitrary_quadrilateral_ptol
                    formulaList[102].idImg = R.drawable.ic_arbitrary_quadrilateral_area2
                    formulaList[103].idImg = R.drawable.ic_circle_inscribed_corners
                    formulaList[104].idImg = R.drawable.ic_circle_one_chord
                    formulaList[105].idImg = R.drawable.ic_circle_intersecting_chords
                    formulaList[106].idImg = R.drawable.ic_circle_angle_bet_secants
                    formulaList[107].idImg = R.drawable.ic_circle_area
                    formulaList[108].idImg = R.drawable.ic_circle_area
                    formulaList[109].idImg = R.drawable.ic_circle_arc_length
                    formulaList[110].idImg = R.drawable.ic_circle_arc_length
                    formulaList[111].idImg = R.drawable.ic_cube
                    formulaList[112].idImg = R.drawable.ic_cube
                    formulaList[113].idImg = R.drawable.ic_cube
                    formulaList[114].idImg = R.drawable.ic_parallelepiped
                    formulaList[115].idImg = R.drawable.ic_parallelepiped
                    formulaList[116].idImg = R.drawable.ic_parallelepiped
                    formulaList[117].idImg = R.drawable.ic_cylinder
                    formulaList[118].idImg = R.drawable.ic_cylinder
                    formulaList[119].idImg = R.drawable.ic_cylinder
                    formulaList[120].idImg = R.drawable.ic_cone
                    formulaList[121].idImg = R.drawable.ic_cone
                    formulaList[122].idImg = R.drawable.ic_cone
                    formulaList[123].idImg = R.drawable.ic_pyramid_
                    formulaList[124].idImg = R.drawable.ic_pyramid_
                    formulaList[125].idImg = R.drawable.ic_sphere
                    formulaList[126].idImg = R.drawable.ic_sphere
                    formulaList[127].idImg = R.drawable.ic_prism
                    formulaList[128].idImg = R.drawable.ic_prism
                    formulaList[129].idImg = R.drawable.ic_prism
                    formulaList[130].idImg = R.drawable.ic_right_triangle_trig
                    formulaList[131].idImg = R.drawable.ic_right_triangle_trig
                    formulaList[132].idImg = R.drawable.ic_right_triangle_trig
                    formulaList[133].idImg = R.drawable.ic_right_triangle_trig
                    INSTANCE?.formulaDao()?.insertAll(formulaList)
                }
            }
        }
    }
}