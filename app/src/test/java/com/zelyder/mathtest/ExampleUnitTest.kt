package com.zelyder.mathtest

import com.zelyder.mathtest.help.FormulaUtilities
import com.zelyder.mathtest.help.PPN
import com.zelyder.mathtest.help.Utilities
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun parseFunc_isCorrect() {
        val util = FormulaUtilities()
        val result: String = util.parseFunc("\\sqrt[n]{a} \\cdot \\sqrt[m]{b} = \\sqrt[n \\cdot m]{a^m b^n}")
        val expected = "[n]/{a} * [m]/{b} = [n * m]/{a^m b^n}"
        assertEquals(expected, result)
    }
    @Test
    fun parseFunc_isCorrect2() {
        val util = FormulaUtilities()
        val result: String = util.parseFunc("\\sqrt[2]{a} \\cdot \\sqrt[m]{b} = \\sqrt[n \\cdot m]{a^m b^n}")
        val expected = "[2]/{a} * [m]/{b} = [n * m]/{a^m b^n}"
        assertEquals(expected, result)
    }
    @Test
    fun replaceBraces_isCorrect(){
        val util = FormulaUtilities()
        val result: String = util.replaceBraces("[n]/{a} * [m]/{b} = [n * m]/{a^m b^n}")
        val expected = "(n)/(a) * (m)/(b) = (n * m)/(a^m b^n)"
        assertEquals(expected, result)
    }
    @Test
    fun equals_isCorrect(){
        val util = FormulaUtilities()
        val result = util.equals("\\sqrt[n]{a} \\cdot \\sqrt[m]{b} = 810 x ",
            "\\sqrt[n]{a} \\cdot \\sqrt[m]{b} = 180 y")
        val expected = false
        assertEquals(expected, result)
    }
}
