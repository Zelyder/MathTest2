package com.zelyder.mathtest.help

import android.util.Log
import android.util.SparseArray
import androidx.core.util.forEach
import java.util.HashMap
import java.util.regex.Pattern

class FormulaUtilities {

    private val arrayOfUndelFunc = SparseArray<String>()
    private val arrayOfDelFunc = SparseArray<String>()
    private val rePlus = Pattern.quote("+")
    private val re = Pattern.quote("?")

    fun getKeys(categoryId: Int): Array<String> {
        return when (categoryId) {
            1 -> arrayOf("&#43", "-", "a", "b", "c", "n", "m", "1", "2", "3")
            2 -> arrayOf("&#43", "-", "a", "b", "n", "m", "1", "2", "\\\\pm", "\\\\mp")
            3 -> arrayOf("&#43", "-", "a", "b", "m", "n", "1", "2", "3")
            4 -> arrayOf("&#43", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            5 -> arrayOf("&#43", "-", "a", "b", "c", "2", "3", "4", "\\\\pm")
            6 -> arrayOf("&#43", "-", "a", "b", "c", "d", "i", "x", "y", "1", "2")
            7 -> arrayOf("&#43", "-", "a", "c", "e", "x", "y", "n", "0", "1")
            8 -> arrayOf("&#43", "-", "a", "b", "c", "h", "p", "r", "R", "m",
                "\\\\sin","\\\\cos","\\\\alpha", "\\\\beta", "\\\\gamma" ,"A","C","1","2","4")
            9 -> arrayOf("&#43", "-", "a", "b", "c", "h", "d", "e", "r", "R", "1", "2")
            10 -> arrayOf("&#43", "-", "a", "1", "2", "3", "4", "5", "6")
            11 -> arrayOf("&#43", "-", "a", "b", "c", "d", "h", "\\\\sin", "\\\\alpha", "\\\\phi",
                "1", "2")
            12 -> arrayOf("&#43", "-", "a", "b", "d", "\\\\sin", "\\\\alpha", "1", "2")
            13 -> arrayOf("&#43", "-", "a", "d", "2", "3", "4")
            14 -> arrayOf("&#43", "-", "a", "h", "r", "d", "A", "H", "B", "1", "2", "3", "4", "\\\\sin",
                "\\\\alpha")
            15 -> arrayOf("&#43", "-", "a", "b", "h", "d", "O", "X", "Y", "A", "B", "C", "D", "\\\\sin",
                "\\\\alpha", "\\\\beta","1","2","3","8","9","0")
            16 -> arrayOf("&#43", "-", "a", "b", "c", "d", "p", "r", "\\\\sin", "\\\\alpha", "\\\\beta", "\\\\gamma"
                , "\\\\delta", "1", "2", "3", "6","8", "0")
            17 -> arrayOf("&#43", "-", "\\\\pi", "r", "\\\\alpha", "\\\\beta", "1", "2", "3", "6","8", "0")
            18 -> arrayOf("&#43", "-", "a", "1", "2", "3", "6","8", "0")
            19 -> arrayOf("&#43", "-","a", "b", "h", "1", "2", "3")
            20 -> arrayOf("&#43", "-", "R", "h", "\\\\pi", "1", "2", "3")
            21 -> arrayOf("&#43", "-", "R", "h", "\\\\ell", "\\\\pi", "1", "2", "3")
            22 -> arrayOf("&#43", "-", "S", "h", "1", "2", "3")
            23 -> arrayOf("&#43", "-", "R", "\\\\pi", "1", "2", "3", "4")
            24 -> arrayOf("&#43", "-", "S", "h", "P", "1", "2", "3")
            25 -> arrayOf("&#43", "-", "a", "b", "c", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot",
                "\\\\alpha")
            26 -> arrayOf("&#43", "-", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "\\\\alpha", "1",
                "2")
            27 -> arrayOf("&#43", "-", "0", "1", "2", "3", "4", "5", "6", "\\\\infty")
            28 -> arrayOf("&#43", "-", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "\\\\alpha",
                "\\\\beta", "1", "2")
            29 -> arrayOf("&#43", "-", "\\\\pm", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "\\\\alpha",
                "1", "2", "3", "4")
            30 -> arrayOf("&#43", "-","\\\\pi", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "\\\\alpha",
                "\\\\beta", "1", "2", "3", "4")
            31 -> arrayOf("&#43", "-", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "\\\\alpha",
                "\\\\beta", "1", "2", "3")
            32 -> arrayOf("&#43", "-", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "1", "2", "3", "4", "8")
            33 -> arrayOf("&#43", "-", "x", "y", "1", "2", "3")
            34 -> arrayOf("&#43", "-", "x", "y", "a", "b", "1", "2", "3")
            35 -> arrayOf("&#43", "-", "\\\\pm", "x", "y", "a", "b", "c", "e", "1", "2", "3")
            36 -> arrayOf("&#43", "-", "\\\\pm", "x", "y", "a", "b", "c", "e", "\\\\pi")
            37 -> arrayOf("&#43", "-", "x", "y", "p","1", "2", "3", "4")
            38 -> arrayOf("&#43", "-", "x", "n", "e", "0", "1", "2", "3")
            39 -> arrayOf("&#43", "-","u", "v", "k", "f", "g", "x", "'", "1", "2", "3")
            40 -> arrayOf("&#43", "-", "\\\\sin", "\\\\cos", "\\\\tan", "\\\\cot", "x", "1", "2", "3")
            41 -> arrayOf("&#43", "-", "x", "1", "2", "3", "4")
            42 -> arrayOf("&#43", "-", "x", "\\\\sinh", "\\\\cosh", "\\\\tanh", "\\\\coth", "1", "2", "3")
            43 -> arrayOf("&#43", "-", "x", "1", "2", "3", "4")
            44 -> arrayOf("&#43", "-", "a", "x", "C", "f", "g", "F", "d", "u", "v", "'", "1", "2", "3")
            45 -> arrayOf("&#43", "-", "a", "b", "c", "d", "x", "C", "n", "\\\\tan", "\\\\tanh", "1", "2", "3")
            46 -> arrayOf("&#43", "-", "x", "C", "m", "n", "\\\\pi", "\\\\sin", "\\\\cos", "\\\\tan",
                "\\\\cot", "1", "2", "3", "4", "6", "8")
            47 -> arrayOf("&#43", "-", "x","C", "\\\\sinh", "\\\\cosh", "\\\\tanh", "\\\\coth", "1", "2", "3")
            48 -> arrayOf("&#43", "-", "x", "e", "a", "b", "C", "n", "\\\\sin", "\\\\cos", "1", "2", "3")
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
        val separateFormula:Array<String> =
            formula.split("[=]".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return collectFormulaUnknown(separateFormula)
    }

    private fun collectFormulaUnknown(separateFormula: Array<String>): String{
        var collectedFormula: String = separateFormula[0]
        for (i in 1 until separateFormula.size){
            collectedFormula += "=" + toUnknown(separateFormula[i])
        }
        return collectedFormula
    }

    private fun collectFormulaDel(separateFormula: Array<String>): String{
        var collectedFormula: String = separateFormula[0]
        var addPart = ""
        for (i in 1 until separateFormula.size){
            addPart += "=" + separateFormula[i]
        }
        collectedFormula += delCharQ(addPart)
        return collectedFormula
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
            tempFormula = tempFormula.replace("♪", "")
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
            formula.contains("\\frac") -> "\\frac"
            formula.contains("\\log") -> "\\log"
            formula.contains("\\ln") -> "\\ln"
            formula.contains("\\lim") -> "\\lim"
            formula.contains("\\to") -> "\\to"
            formula.contains("\\left") -> "\\left"
            formula.contains("\\right") -> "\\right"
            formula.contains("=") -> "="
            formula.contains("base") -> "base"
            formula.contains("side surface") -> "side surface"
            else -> ""
        }
    }

    private fun getDelFunc(formula: String): String {
        return when {
            formula.contains("\\sin") -> "\\sin"
            formula.contains("\\cos") -> "\\cos"
            formula.contains("\\tg") -> "\\tg"
            formula.contains("\\tan") -> "\\tan"
            formula.contains("\\ctg") -> "\\ctg"
            formula.contains("\\cot") -> "\\cot"
            formula.contains("\\alpha") -> "\\alpha"
            formula.contains("\\beta") -> "\\beta"
            formula.contains("\\gamma") -> "\\gamma"
            formula.contains("\\delta") -> "\\delta"
            formula.contains("\\ell") -> "\\ell"
            formula.contains("\\pi") -> "\\pi"
            formula.contains("\\pm") -> "\\pm"
            formula.contains("\\mp") -> "\\mp"
            formula.contains("\\phi") -> "\\phi"
            formula.contains("\\infty") -> "\\infty"
            formula.contains("\\sinh") -> "\\sinh"
            formula.contains("\\cosh") -> "\\cosh"
            formula.contains("\\tanh") -> "\\tanрh"
            formula.contains("\\coth") -> "\\coth"
            formula.contains("&#43") -> "&#43"
            formula.contains("&#45") -> "&#45"
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
        return collectFormulaDel(separateFormula)
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
        var delFunc = getDelFunc(tempFormula)
        while (delFunc != "") {
            arrayOfDelFunc.append(tempFormula.indexOf(delFunc), delFunc)
            tempFormula = tempFormula.replaceFirst(delFunc, "◇")
            delFunc = getDelFunc(tempFormula)
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
                while (j < outStr.length && outStr[j] != ' ' && outStr[j] != '[' && outStr[j] != '{'
                    && outStr[j] != '(') {
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
                    "cos" -> {
                        outStr = outStr.replace(indexBackslash, i, "€/")
                        i -= i-indexBackslash
                    }
                    "tan" -> {
                        outStr = outStr.replace(indexBackslash, i, "†/")
                        i -= i-indexBackslash
                    }
                    "cot" -> {
                        outStr = outStr.replace(indexBackslash, i, "‡/")
                        i -= i-indexBackslash
                    }
                    "sinh" -> {
                        outStr = outStr.replace(indexBackslash, i, "œ/")
                        i -= i-indexBackslash
                    }
                    "cosh" -> {
                        outStr = outStr.replace(indexBackslash, i, "ќ/")
                        i -= i-indexBackslash
                    }
                    "tanh" -> {
                        outStr = outStr.replace(indexBackslash, i, "ž/")
                        i -= i-indexBackslash
                    }
                    "coth" -> {
                        outStr = outStr.replace(indexBackslash, i, "Ÿ/")
                        i -= i-indexBackslash
                    }
                    "alpha" -> {
                        outStr = outStr.replace(indexBackslash, i, "α")
                        i -= i-indexBackslash
                    }
                    "infty" -> {
                        outStr = outStr.replace(indexBackslash, i, "∞")
                        i -= i-indexBackslash
                    }
                    "beta" -> {
                        outStr = outStr.replace(indexBackslash, i, "β")
                        i -= i-indexBackslash
                    }
                    "gamma" -> {
                        outStr = outStr.replace(indexBackslash, i, "γ")
                        i -= i-indexBackslash
                    }
                    "delta" -> {
                        outStr = outStr.replace(indexBackslash, i, "δ")
                        i -= i-indexBackslash
                    }
                    "pi" -> {
                        outStr = outStr.replace(indexBackslash, i, "π")
                        i -= i-indexBackslash
                    }
                    "ell" -> {
                        outStr = outStr.replace(indexBackslash, i, "ℓ")
                        i -= i-indexBackslash
                    }
                    "phi" -> {
                        outStr = outStr.replace(indexBackslash, i, "φ")
                        i -= i-indexBackslash
                    }

                    else -> insetDelAfter = ' '

                }
            }
            if (i + 1 < outStr.length && isNum(outStr[i]) ){
                val functionStr = StringBuilder()
                functionStr.append(outStr[i])
                indexBackslash = i
                var j = i + 1
                while (j < outStr.length && isNum(outStr[j])){
                    functionStr.append(outStr[j])
                    i = j + 1
                    j++
                }
                when (functionStr.toString()) {
                    "180" -> {
                        outStr = outStr.replace(indexBackslash, i, "Ґ")
                        i -= i-indexBackslash
                    }
                    "360" -> {
                        outStr = outStr.replace(indexBackslash, i, "¦")
                        i -= i-indexBackslash
                    }
                    "90" -> {
                        outStr = outStr.replace(indexBackslash, i, "љ")
                        i -= i-indexBackslash
                    }
                    "32" -> {
                        outStr = outStr.replace(indexBackslash, i, "™")
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
                && correctTemp[i] != '^' && correctTemp[i] != '(' && correctTemp[i] != ')' ) {

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

    fun isNum(ch: Char): Boolean{
        return when(ch){
            '0' -> true
            '1' -> true
            '2' -> true
            '3' -> true
            '4' -> true
            '5' -> true
            '6' -> true
            '7' -> true
            '8' -> true
            '9' -> true

            else -> false
        }

    }

    fun replaceBraces(string: String):String{
        var outStr = string
        outStr = outStr.replace("_","/")
        outStr = outStr.replace("[","(")
        outStr = outStr.replace("]",")")
        outStr = outStr.replace("{","(")
        outStr = outStr.replace("}",")")
        outStr = outStr.replace("&#43","+")
        outStr = outStr.replace("&#45","-")
        outStr = outStr.replace(" ","")
        return outStr

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

fun MyMathView.setupMathView() {
    this.settings.defaultFontSize = (26 * resources.configuration.fontScale).toInt()
    val text = ("<html><head>"
            + "<style type=\"text/css\">body{color: #fff; background-color: #000;}"
            + "</style></head>"
            + "<body>"
            + "Test"
            + "</body></html>")

    this.loadData(text, "text/html", "utf-8")

}

