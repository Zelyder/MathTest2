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
    private val re = Pattern.quote("?")

    fun getKeys(categoryId: Int): Array<String>{
        return when(categoryId){
            1 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            2 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            3 -> arrayOf("+", "-", "a", "b", "m", "n", "1", "2", "3")
            4 -> arrayOf("+", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            else -> arrayOf()
        }
    }

    fun toUnknown(formula:String): String {
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
            formula.contains("\\log") -> "\\log"
            formula.contains("\\ln") -> "\\ln"
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
            else -> ""
        }
    }

    private fun showDelFunc(formula: String): String {
        var tempFormula = formula
        for (i in 0 until arrayOfDelFunc.size()) {
            tempFormula = tempFormula.replaceFirst(
                "◇",
                arrayOfDelFunc.get(arrayOfDelFunc.keyAt(i))
            )
            arrayOfDelFunc.delete(i)
        }
        return tempFormula
    }

    fun addCharQ(formula: String, ch: String): String {
        var tempFormula = formula
        tempFormula = clearString(tempFormula)
        var isFind = false
        var i = 0
        while (!isFind) {
            if (i >= tempFormula.length) {
                isFind = true
            } else if (tempFormula[i] == '?') {
                tempFormula = tempFormula.replaceFirst(re.toRegex(), ch)
                isFind = true
                //checkFull(Formula);
            }
            i++
        }
        return tempFormula
    }

    private fun clearString(formula: String): String {
        var tempFormula = formula
        tempFormula = tempFormula.replace("$$", "")
        return tempFormula
    }

    fun delCharQRight(formula: String): String {
        var tempFormula = formula
        tempFormula = clearString(tempFormula)
        val separateFormula =
            tempFormula.split("[=]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return separateFormula[0] + "=" + delCharQ(separateFormula[1])
    }

    fun delCharQ(formula: String): String {
        var tempFormula = formula
        tempFormula = clearString(tempFormula)
        var isFind = false
        while (getUndelFunc(tempFormula) != "") {
            tempFormula = hideUndelFunc(tempFormula)
        }
        tempFormula = hideDelFunc(tempFormula)
        tempFormula = tempFormula.reversed()
        var i = 0
        while (!isFind) {
            if (i >= tempFormula.length) {
                isFind = true
            } else if (isNormal(tempFormula[i])) {
                tempFormula = if (tempFormula[i] == '+') {
                    tempFormula.replaceFirst(rePlus.toRegex(), "?")
                } else {
                    tempFormula.replaceFirst(tempFormula[i].toString().toRegex(), "?")
                }
                isFind = true

            }
            i++
        }
        return showAllFunc(tempFormula.reversed())
    }

    fun delChar(formula: String): String {
        var tempFormula = formula
        tempFormula = clearString(tempFormula)
        var isFind = false
        while (getUndelFunc(tempFormula) != "") {
            tempFormula = hideUndelFunc(tempFormula)
        }
        tempFormula = hideDelFunc(tempFormula)
        tempFormula = tempFormula.reversed()
        var i = 0
        while (!isFind) {
            if (i >= tempFormula.length) {
                isFind = true
            } else if (isNormal(tempFormula[i])) {
                if (tempFormula[i] == '+') {
                    tempFormula = tempFormula.replaceFirst(rePlus.toRegex(), "")
                } else {
                    tempFormula = tempFormula.replaceFirst(tempFormula[i].toString().toRegex(), "")
                }
                isFind = true
            }
            i++
        }
        return showAllFunc(tempFormula.reversed())
    }

    private fun hideDelFunc(formula: String): String {
        var tempFormula = formula
        val delFunc = getDelFunc(tempFormula)
        while (getDelFunc(tempFormula) != "") {
            arrayOfDelFunc.append(tempFormula.indexOf(delFunc), delFunc)
            tempFormula = tempFormula.replaceFirst(delFunc.toRegex(), "◇")
        }
        return tempFormula
    }

    private fun showAllFunc(formula: String): String {
        var tempFormula = formula
        tempFormula = showUndelFunc(tempFormula)
        tempFormula = showDelFunc(tempFormula)
        return tempFormula
    }

    fun equals(){

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

