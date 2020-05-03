package com.zelyder.mathtest.help

fun fromSubcategoryIdToCategoryId(subcategoryId: Int): Int{
    return when(subcategoryId){
        in 1..6 -> 1
        in 8..17 -> 2
        in 18..24 -> 3
        in 25..32 -> 4
        in 33..37 -> 5
        in 38..43 -> 6
        in 44..48 -> 7
        else -> 0
    }
}