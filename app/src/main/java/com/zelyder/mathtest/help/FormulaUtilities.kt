package com.zelyder.mathtest.help

import android.util.Log
import android.util.SparseArray
import androidx.core.util.forEach
import io.github.kexanie.library.MathView
import java.util.HashMap
import java.util.regex.Pattern

class FormulaUtilities {

    private val arrayOfUndelFunc = SparseArray<String>()
    private val arrayOfDelFunc = SparseArray<String>()
    private val rePlus = Pattern.quote("+")
    private val re = Pattern.quote("?")

    fun getKeys(categoryId: Int): Array<String> {
        return when (categoryId) {
            1 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            2 -> arrayOf("+", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            3 -> arrayOf("+", "-", "a", "b", "m", "n", "1", "2", "3")
            4 -> arrayOf("+", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            5 -> arrayOf("+", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            6 -> arrayOf("+", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            7 -> arrayOf("+", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            8 -> arrayOf("+", "-", "a", "b", "c", "sin", "2", "n", "0", "1")
            else -> arrayOf()
        }
    }

    fun toUnknown(formula: String): String {
        var tempFormula = formula
        for (ch in tempFormula) {
            if (isNormal(ch)) {
                when {
                    ch == '+' -> run {
                        tempFormula = tempFormula.replaceFirst(rePlus.toRegex(), "?")
                    }
                    getDelFunc(tempFormula) != "" -> run {
                        tempFormula = tempFormula
                            .replaceFirst(getDelFunc(tempFormula), "?")
                    }
                    getUndelFunc(tempFormula) != "" -> run {
                        tempFormula = hideUndelFunc(tempFormula)
                        Log.d("LOL", "after hide: $tempFormula")
                    }
                    else -> run {
                        Log.d("LOL", "before replace ?: $formula")
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

    /*
        fun equals(checkableStr: String, correctStr: String): Boolean {
            val correctRight =
                correctStr.split("[=]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            val checkableRight =
                checkableStr.split("[=]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

            val map = HashMap<String, Int>()

            var i = 0
            while (i < correctRight.length) {
                if (correctRight[i] != '/' && correctRight[i] != '+') {
                    map[correctRight[i].toString()] = i + 1
                }
                i++
            }
            return try {
                calculate(map, correctRight) == calculate(map, checkableRight)
            } catch (e: Exception) {
                e.message
                false
            }

        }
    */
    public fun parseFunc(formula: String): String {
        var outStr = java.lang.StringBuilder(formula)
        var i = 0
        var indexBackslash: Int
        var insetDelAfter = ' '
        while (i < outStr.length) {
            if (outStr[i] == '\\' && i + 1 < outStr.length) {
                val functionStr = StringBuilder()
                indexBackslash = i
                var j = i + 1
                while (outStr[j] != ' ' && outStr[j] != '[' && outStr[j] != '{') {
                    functionStr.append(outStr[j])
                    i = j + 1
                    j++
                }
                when (functionStr.toString()) {
                    "frac" -> {
                        insetDelAfter = '}'
                        outStr = outStr.replace(indexBackslash, i, "")
                        i -= i-indexBackslash
                    }
                    "sqrt" -> {
                        if (outStr[i] == '[') {
                            insetDelAfter = ']'
                        } else if (outStr[i] == '{') {
                            insetDelAfter = ' '
                        }
                        outStr = outStr.replace(indexBackslash, i, "")
                        i -= i-indexBackslash
                    }
                    "log" -> {
                        if (outStr[i + 1] == '{') {
                            insetDelAfter = '}'
                        }
                        outStr = outStr.replace(indexBackslash, i, "")
                        i -= i-indexBackslash
                    }
                    "cdot" -> {
                        outStr = outStr.replace(indexBackslash, i, "*")
                        i -= i-indexBackslash
                    }
                    "pm" -> {
                        outStr = outStr.replace(indexBackslash, i, "-")
                        i -= i-indexBackslash
                    }
                    "mp" -> {
                        outStr = outStr.replace(indexBackslash, i, "-")
                        i -= i-indexBackslash
                    }
                    "sin" -> {
                        outStr = outStr.replace(indexBackslash, i, "Š/")
                        i -= i-indexBackslash
                    }
                    "alpha" -> {
                        outStr = outStr.replace(indexBackslash, i, "α")
                        i -= i-indexBackslash
                    }
                    else -> insetDelAfter = ' '

                }
            }
            if (insetDelAfter != ' ' && insetDelAfter == outStr[i]) {
                insetDelAfter = ' '
                outStr = outStr.insert(i+1, "/")

            }
            i++
        }
        return outStr.toString()
    }

    fun equals(checkableStr: String, correctStr: String): Boolean {
        var checkableTemp = clearString(checkableStr).split("[=]"
            .toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        var correctTemp = clearString(correctStr).split("[=]"
            .toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

        val map = HashMap<String, Int>()

        checkableTemp = parseFunc(checkableTemp)
        checkableTemp = replaceBraces(checkableTemp)

        correctTemp = parseFunc(correctTemp)
        correctTemp = replaceBraces(correctTemp)

        var i = 0
        while (i < correctTemp.length) {
            if (correctTemp[i] != '/' && correctTemp[i] != '+' && correctTemp[i] != '-'
                && correctTemp[i] != '^' && correctTemp[i] != '(' && correctTemp[i] != ')') {
                map[correctTemp[i].toString()] = i + 1
            }
            i++
        }


        return calculate(map,correctTemp) == calculate(map,checkableTemp)
    }

    private fun calculate(map: Map<String, Int>, string: String): Float {
        val outputStr = StringBuilder()
        val ppn = PPN()

        var i = 0
        while (i < string.length) {
            when(string[i]){
                '+' -> outputStr.append('+')
                '-' -> outputStr.append('-')
                '(' -> outputStr.append('(')
                ')' -> outputStr.append(')')
                '^' -> outputStr.append('^')
                '*' -> outputStr.append('*')
                '/' -> outputStr.append('/')
                else -> {
                    if (map.containsKey(string[i].toString())){
                        outputStr.append(map[string[i].toString()])
                    }else{
                        return 0f
                    }
                }
            }
            if (outputStr.isNotEmpty() && i + 1 < string.length
                && string[i] != '+' && string[i] != '-' && string[i] != '^'
                && string[i] != '(' && string[i] != ')'&& string[i] != '*') {

                if (string[i + 1] != '/' && string[i + 1] != '+' && string[i+ 1] != '^'
                    && string[i + 1] != '-' && string[i + 1] != '(' && string[i + 1] != ')'
                    && string[i + 1] != '*'
                ) {
                    outputStr.append('*')
                }
            }else if (outputStr.isNotEmpty() && i + 1 < string.length
                && string[i] == ')' && string[i + 1] != '/' && string[i + 1] != '+'
                && string[i + 1] != '-'  && string[i + 1] != ')'){
                outputStr.append('*')
            }
            i++
        }
        return ppn.eval(outputStr.toString())
    }

    fun replaceBraces(string: String):String{
        var outStr = string
        outStr = outStr.replace("_","/")
        outStr = outStr.replace("[","(")
        outStr = outStr.replace("]",")")
        outStr = outStr.replace("{","(")
        outStr = outStr.replace("}",")")
        outStr = outStr.replace(" ","")
        return outStr
        //what?
    }


}

/*
private fun calculate(map: Map<String, Int>, string: String): Float {
    val outputStr = StringBuilder()
    val ppn = PPN()

    var i = 0
    while (i < string.length) {
        if (isNormal(string[i])) {
            if (string[i] == '#') {
                val multiChars = StringBuilder()
                var j = i + 1
                while (string[j] != '#') {
                    multiChars.append(string[j])
                    i = j + 1
                    j++
                }
                if (map.containsKey(multiChars.toString())) {
                    outputStr.append(map[multiChars.toString()])
                } else {
                    outputStr.append("999")
                }

            } else if (string[i] == '+') {
                outputStr.append('+')
            } else if (string[i] == '-') {
                outputStr.append('-')
            } else if (string[i] == '(') {
                outputStr.append('(')
            } else if (string[i] == ')') {
                outputStr.append(')')
            } else if (string[i] == '²') {
                outputStr.append('²')
            } else if (string[i] == 'Δ') {
                outputStr.append('Δ')
            } else if (string[i] != '/' && !map.containsKey(string[i].toString())) {
                outputStr.append("999")
            } else {
                if (map.containsKey(string[i].toString())) {
                    outputStr.append(map[string[i].toString()])
                }
            }

            if (outputStr.length > 0 && i + 1 < string.length
                && isNormal(string[i + 1]) && string[i] != '+'
                && string[i] != '-' && string[i] != '('
                && string[i] != ')' && string[i] != 'Δ'
            ) {
                if (string[i] == '/') {
                    outputStr.append('/')
                } else if (string[i + 1] != '/' && string[i + 1] != '+'
                    && string[i + 1] != '-' && string[i + 1] != '²'
                    && string[i + 1] != '(' && string[i + 1] != ')'
                ) {
                    outputStr.append('*')
                }
            }
        }
        i++
    }
    Log.d("LOL", string)
    Log.d("LOL", outputStr.toString())
    Log.d("LOL", "почситали: " + ppn.eval(outputStr.toString()))
    return ppn.eval(outputStr.toString())
}

*/
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

