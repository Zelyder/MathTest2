package com.zelyder.mathtest.help;

import java.util.LinkedList;

public class PPN {
    private static boolean isDelim(char c) {
        return c == ' ';
    }
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }
    private static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
    private static void processOperator(LinkedList<Float> st, char op) {
        float r;
        float l = 1;
        if(op == 'Δ'  && st.isEmpty()){
            r = 0;
            l = 0;
        }else {
            r = st.removeLast();
        }

        if(op != '²' && op != 'Δ' && !st.isEmpty()) {
            l = st.removeLast();
        }
        if(op == '-' && l == 1){
            l = 0;
        }
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
            case '%':
                st.add(l % r);
                break;
            case '^':
                st.add((float) Math.pow(l,r));
                break;
        }
    }
    public float eval(String s) {
        LinkedList<Float> st = new LinkedList<>();
        LinkedList<Character> op = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isDelim(c))
                continue;
            if (c == '(')
                op.add('(');
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st,op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                StringBuilder operand = new StringBuilder();
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    operand.append(s.charAt(i++));
                --i;
                st.add(Float.parseFloat(operand.toString()));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return st.get(0);
    }
}