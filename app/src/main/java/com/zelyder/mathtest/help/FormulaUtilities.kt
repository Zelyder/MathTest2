package com.zelyder.mathtest.help

import android.util.Log
import android.util.SparseArray
import androidx.core.util.forEach
import io.github.kexanie.library.MathView
import java.util.regex.Pattern

class FormulaUtilities {

    private val arrayOfUndelFunc = SparseArray<String>()
    private val arrayOfDelFunc = SparseArray<String>()
    private val rePlus = Pattern.quote("+")

    fun getKeys(categoryId: Int): Array<String>{
        return when(categoryId){
            1 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            2 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            3 -> arrayOf("+", "-", "a", "b", "m", "n", "1", "2", "3")
            else -> arrayOf()
        }
    }

    fun toUnknown(formula:String): String{
        var tempFormula = formula
        for(ch in tempFormula) {
            if(isNormal(ch)){
                when {
                    ch == '+' -> run {
                        tempFormula = tempFormula.replaceFirst(rePlus.toRegex(), "?")
                    }
                    getDelFunc(tempFormula) != "" -> run { tempFormula = tempFormula
                        .replaceFirst(getDelFunc(tempFormula), "?")
                    }
                    getUndelFunc(tempFormula) != "" -> run {
                        tempFormula = hideUndelFunc(tempFormula)
                        Log.d("LOL", "after hide: $tempFormula")
                    }
                    else -> run {
                        Log.d("LOL","before replace ?: $formula")
                        tempFormula = tempFormula.replaceFirst(ch.toString(), "?")
                    }
                }

            }
        }
        Log.d("LOL", "pre final toUnknown: $tempFormula")
        tempFormula = tempFormula.replace("♪", "")
        tempFormula = showUndelFunc(tempFormula)
        Log.d("LOL", "final toUnknown: $tempFormula")
        return tempFormula
    }

    fun toUnknownRight(formula: String): String {
        val separateFormula =
            formula.split("[=]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return separateFormula[0] + "=" + toUnknown(separateFormula[1])
    }


    private fun hideUndelFunc(formula: String): String {
        var tempFormula = formula
        val undelFunc = getUndelFunc(tempFormula)
        val id = tempFormula.indexOf(undelFunc)
        tempFormula = tempFormula.replaceFirst(undelFunc, "#${"♪".repeat(undelFunc.length - 1)}")
        Log.d("LOL", "id $id")
        arrayOfUndelFunc.append(id, undelFunc)
        Log.d("LOL", arrayOfUndelFunc.toString())
        return tempFormula
    }

    private fun showUndelFunc(formula: String): String {
        var tempFormula = formula
        arrayOfUndelFunc.forEach { _, value ->
            tempFormula = tempFormula.replaceFirst(
                "#",
                value
            )
        }
        arrayOfUndelFunc.clear()
        return tempFormula
    }


    private fun isNormal(ch: Char): Boolean {
        return (ch != '=' && ch != '/' && ch != ' ' && ch != '?' && ch != '^' && ch != '_'
                && ch != '`' && ch != '(' && ch != ')' && ch != '{' && ch != '}' && ch != '#'
                && ch != '<' && ch != '>' && ch != '!' && ch != '∓' && ch != '|' && ch != '['
                && ch != ']' && ch != '$' && ch != '♪')
    }
    private fun getUndelFunc(formula: String): String {
        return when {
            formula.contains("\\sum") -> "\\sum"
            formula.contains("\\sqrt") -> "\\sqrt"
            formula.contains("\\root") -> "\\root"
            formula.contains("\\int") -> "\\int"
            formula.contains("\\in") -> "\\in"
            formula.contains("\\cdot") -> "\\cdot"
            formula.contains("\\pm") -> "\\pm"
            formula.contains("\\mp") -> "\\mp"
            formula.contains("\\frac") -> "\\frac"
            else -> ""
        }
    }

    private fun getDelFunc(formula: String): String {
        return when {
            formula.contains("\\sin") -> "\\sin"
            formula.contains("\\cos") -> "\\cos"
            formula.contains("\\alpha") -> "\\alpha"
            formula.contains("\\beta") -> "\\beta"
            formula.contains("\\gamma") -> "\\gamma"
            formula.contains("\\pi") -> "\\pi"
            formula.contains("\\log") -> "\\log"
            else -> ""
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

