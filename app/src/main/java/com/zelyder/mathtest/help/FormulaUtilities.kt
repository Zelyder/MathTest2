package com.zelyder.mathtest.help


import com.nishant.math.MathView

class FormulaUtilities {

    fun getKeys(categoryId: Int): Array<String>{
        return when(categoryId){
            1 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            2 -> arrayOf("+", "-", "a", "b", "c", "d", "f", "1", "2", "3")
            else -> arrayOf()
        }
    }

}
fun MathView.setupMathView() {
    /*this.settings.defaultFontSize = (22 * resources.configuration.fontScale).toInt()
    this.isLongClickable = true
    this.setOnLongClickListener { true }
    this.isLongClickable = false
    this.isClickable = true
    this.setOnClickListener { true }
    this.isClickable = false

     */
}