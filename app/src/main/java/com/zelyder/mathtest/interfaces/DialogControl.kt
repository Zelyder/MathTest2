package com.zelyder.mathtest.interfaces

interface DialogControl {
    fun createCorrectDialog(text: String)
    fun createFinalDialog(correctAnswers: Int, wrongAnswers: Int)
}