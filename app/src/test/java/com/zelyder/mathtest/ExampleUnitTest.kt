package com.zelyder.mathtest

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
    fun replace_isCorrect() {
        val util = Utilities()
        val result: String = util.checkBackslash("\$\$a^{-m} = \\frac{1}{a^m}\$\$")
        val expected = "\$\$a^{-m} = #{1}{a^m}\$\$"
        assertEquals(expected, result)

    }
}
