package com.zelyder.mathtest.help

import java.util.*

class PPN {
    fun eval(s: String): Float {
        val st = LinkedList<Float>()
        val op = LinkedList<Char>()
        var i = 0
        while (i < s.length) {
            val c = s[i]
            if (isDelim(c)) {
                i++
                continue
            }
            if (c == '(') op.add('(') else if (c == ')') {
                while (op.last != '(') processOperator(st, op.removeLast())
                op.removeLast()
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.last) >= priority(
                        c
                    )
                ) processOperator(st, op.removeLast())
                op.add(c)
            } else {
                val operand = StringBuilder()
                while (i < s.length && Character.isDigit(s[i])) operand.append(s[i++])
                --i
                st.add(operand.toString().toFloat())
            }
            i++
        }
        while (!op.isEmpty()) processOperator(st, op.removeLast())
        return st[0]
    }

    companion object {
        private fun isDelim(c: Char): Boolean {
            return c == ' '
        }

        private fun isOperator(c: Char): Boolean {
            return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^'
        }

        private fun priority(op: Char): Int {
            return when (op) {
                '+', '-' -> 1
                '*', '/', '%' -> 2
                '^' -> 3
                else -> -1
            }
        }

        private fun processOperator(
            st: LinkedList<Float>,
            op: Char
        ) {
            var r = 1f
            var l = 1f
            if (op == 'Δ' && st.isEmpty()) {
                r = 0f
                l = 0f
            } else if (!st.isEmpty()) {
                r = st.removeLast()
            }
            if (op != '²' && op != 'Δ' && !st.isEmpty()) {
                l = st.removeLast()
            }
            if (op == '-' && l == 1f) {
                l = 0f
            }
            when (op) {
                '+' -> st.add(l + r)
                '-' -> st.add(l - r)
                '*' -> st.add(l * r)
                '/' -> st.add(l / r)
                '%' -> st.add(l % r)
                '^' -> st.add(Math.pow(l.toDouble(), r.toDouble()).toFloat())
            }
        }
    }
}