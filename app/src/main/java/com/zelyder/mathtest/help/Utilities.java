package com.zelyder.mathtest.help;

import android.util.SparseArray;

import java.util.regex.Pattern;

public class Utilities {

    private final String re = Pattern.quote("?");
    private final String rePlus = Pattern.quote("+");
    private SparseArray<String> arrayOfUndelFunc = new SparseArray<>();
    private SparseArray<String> arrayOfDelFunc = new SparseArray<>();

    public String toUnknown(String formula) {
        for (int i = 0; i < formula.length(); i++) {
            if (isNormal(formula.charAt(i))) {
                if (formula.charAt(i) == '+') {
                    formula = formula.replaceFirst(rePlus, "?");
                } else if (!getDelFunc(formula).equals("")) {
                    formula = formula.replaceFirst(getDelFunc(formula), "?");
                } else if (!getUndelFunc(formula).equals("")) {
                    formula = hideUndelFunc(formula);
                } else {
                    formula = formula.replaceFirst(String.valueOf(formula.charAt(i)), "?");
                }

            }
        }
        formula = showUndelFunc(formula);
        return formula;
    }

    public String clearString(String formula) {
        return formula.replace("`", "");
    }

    public String addCharQ(String formula, String ch) {
        boolean isFind = false;
        for (int i = 0; !isFind; i++) {
            if (i >= formula.length()) {
                isFind = true;
            } else if (formula.charAt(i) == '?') {
                formula = formula.replaceFirst(re, ch);
                isFind = true;
                //checkFull(Formula);
            }
        }
        return formula;
    }

    public String delCharQ(String formula) {
        boolean isFind = false;
        while (!getUndelFunc(formula).equals("")) {
            formula = hideUndelFunc(formula);
        }
        formula = hideDelFunc(formula);
        formula = reverse(formula);
        for (int i = 0; !isFind; i++) {
            if (i >= formula.length()) {
                isFind = true;
            } else if (isNormal(formula.charAt(i))) {
                if (formula.charAt(i) == '+') {
                    formula = formula.replaceFirst(rePlus, "?");
                } else {
                    formula = formula.replaceFirst(String.valueOf(formula.charAt(i)), "?");
                }
                isFind = true;

            }
        }
        return showAllFunc(reverse(formula));
    }

    public String delChar(String formula) {
        formula = clearString(formula);
        boolean isFind = false;
        while (!getUndelFunc(formula).equals("")) {
            formula = hideUndelFunc(formula);
        }
        formula = hideDelFunc(formula);
        formula = reverse(formula);
        for (int i = 0; !isFind; i++) {
            if (i >= formula.length()) {
                isFind = true;
            } else if (isNormal(formula.charAt(i))) {
                if (formula.charAt(i) == '+') {
                    formula = formula.replaceFirst(rePlus, "");
                } else {
                    formula = formula.replaceFirst(String.valueOf(formula.charAt(i)), "");
                }
                isFind = true;
            }
        }
        return showAllFunc(reverse(formula));
    }

    private boolean isNormal(char ch) {
        return ch != '=' && ch != '/' && ch != ' ' && ch != '?' && ch != '^' && ch != '_'
                && ch != '`' && ch != '(' && ch != ')' && ch != '{' && ch != '}' && ch != '#'
                && ch != '<' && ch != '>' && ch != '!';
    }


    private String getUndelFunc(String formula) {
        if (formula.contains("sum")) {
            return "sum";
        } else if (formula.contains("sqrt")) {
            return "sqrt";
        } else if (formula.contains("root")) {
            return "root";
        } else if (formula.contains("int")) {
            return "int";
        } else if (formula.contains("/_")) {
            return "/_";
        } else if (formula.contains("/_\\")) {
            return "/_\\";
        } else if (formula.contains("in")) {
            return "in";
        } else if (formula.contains("~~")) {
            return "~~";
        } else if (formula.contains("abs")) {
            return "abs";
        } else if (formula.contains("->")) {
            return "->";
        }
        return "";
    }

    private String getDelFunc(String formula) {
        if (formula.contains("sin")) {
            return "sin";
        } else if (formula.contains("cos")) {
            return "cos";
        } else if (formula.contains("+-")) {
            return "+-";
        } else if (formula.contains("alpha")) {
            return "alpha";
        } else if (formula.contains("beta")) {
            return "beta";
        } else if (formula.contains("gamma")) {
            return "gamma";
        } else if (formula.contains("pi")) {
            return "pi";
        }else if (formula.contains("log")) {
            return "log";
        }
        return "";
    }


    private String reverse(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        string = builder.toString();
        return string;
    }

    private String showUndelFunc(String formula) {
        for (int i = 0; i < arrayOfUndelFunc.size(); i++) {
            formula = formula.replaceFirst("#", arrayOfUndelFunc.get(arrayOfUndelFunc.keyAt(i)));
            arrayOfUndelFunc.delete(i);
        }
        return formula;
    }

    private String hideUndelFunc(String formula) {
        String undelFunc = getUndelFunc(formula);
        arrayOfUndelFunc.append(formula.indexOf(undelFunc), undelFunc);
        formula = formula.replaceFirst(undelFunc, "#");
        return formula;
    }

    private String hideDelFunc(String formula) {
        String delFunc = getDelFunc(formula);
        while (!getDelFunc(formula).equals("")) {
            arrayOfDelFunc.append(formula.indexOf(delFunc), delFunc);
            formula = formula.replaceFirst(delFunc, "◇");
        }
        return formula;
    }

    private String showDelFunc(String formula) {
        for (int i = 0; i < arrayOfDelFunc.size(); i++) {
            formula = formula.replaceFirst("◇", arrayOfDelFunc.get(arrayOfDelFunc.keyAt(i)));
            arrayOfDelFunc.delete(i);
        }
        return formula;
    }

    private String showAllFunc(String formula) {
        formula = showUndelFunc(formula);
        formula = showDelFunc(formula);
        return formula;
    }


}
