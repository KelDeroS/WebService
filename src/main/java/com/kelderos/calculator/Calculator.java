package main.java.com.kelderos.calculator;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.Parameter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    public String checkPriority(Stack<Character> s)
    {
        char tmp, tmp1;
        String out = "";
        tmp = s.pop();
        if (s.isEmpty())
        {
            s.push(tmp);
            return out;
        }
        if ((tmp == '+') || (tmp == '-'))
        {
            tmp1 = s.pop();
            while ((tmp1 == '+') || (tmp1 == '-') || (tmp1 == '*') || (tmp1 == '/'))
            {
                out += tmp1;
                if (s.isEmpty())
                {
                    break;
                }
                tmp1 = s.pop();
            }
            if (tmp1 == '(')
            {
                s.push(tmp1);
            }
            s.push(tmp);
            return out;
        }
        if ((tmp == '*') || (tmp == '/'))
        {
            tmp1 = s.pop();
            while ((tmp1 == '*') || (tmp1 == '/'))
            {
                out += tmp1;
                if (s.isEmpty())
                {
                    break;
                }
                tmp1 = s.pop();
            }
            if ((tmp1 == '(') || (tmp1 == '+') || (tmp1 == '-'))
            {
                s.push(tmp1);
            }
            s.push(tmp);
            return out;
        }
        return out;
    }

    public boolean CheckSign(String input, int j)
    {
        int i = j;
        if ((i - 1) == -1)
        {
            return false;
        }
        while (input.charAt(i - 1) == ' ')
        {
            i--;
            if ((i - 1) == -1)
            {
                return false;
            }
        }
        if ((input.charAt(i - 1) == '+') || (input.charAt(i - 1) == '-') || (input.charAt(i - 1) == '*') || (input.charAt(i - 1) == '/'))
        {
            return true;
        }
        else
        {
            i = j;
            while (input.charAt(i + 1) == ' ')
            {
                i++;
            }
            if (input.charAt(i + 1) == ')')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public ArrayList<String> calculate(ArithmeticExpression arithmeticExpression)
    {
        ArrayList<String> results = new ArrayList<>();
        for (int k = 0; k < arithmeticExpression.getExpressions().size(); k++) {
            String currentExpression = arithmeticExpression.getExpressions().get(k);
            String toQueue = "", pusher = "", checker = "", outputString = "";
            Parameter inputVariable = new Parameter();
            Queue<String> output = new LinkedList<>();
            Stack<Character> s = new Stack();
            int i;
            boolean Unar = true;
            short BracketsOpenedC = 0;
            char tmp;
            Unar = true;
            for (i = 0; i < currentExpression.length(); i++) {
                while (((currentExpression.charAt(i) <= 'z') && (currentExpression.charAt(i) >= 'a')) || ((currentExpression.charAt(i) <= 'Z') && (currentExpression.charAt(i) >= 'A')) || ((currentExpression.charAt(i) >= '0') && (currentExpression.charAt(i) <= '9'))) {
                    toQueue += currentExpression.charAt(i);
                    i++;
                    if (i == currentExpression.length())
                    {
                        break;
                    }
                }
                if (toQueue.length() != 0) {
                    i--;
                    output.add(toQueue);
                    toQueue = "";
                    Unar = false;
                    continue;
                }
                if (currentExpression.charAt(i) == '(') {
                    s.push(currentExpression.charAt(i));
                    if (currentExpression.charAt(i) == '(') {
                        BracketsOpenedC++;
                    }
                    Unar = true;
                    continue;
                }
                if (currentExpression.charAt(i) == ')') {
                    BracketsOpenedC--;
                    tmp = s.pop();
                    while (tmp != '(') {
                        pusher += tmp;
                        tmp = s.pop();
                    }
                    output.add(pusher);
                    pusher = "";
                    continue;
                }
                if ((currentExpression.charAt(i) == '+') || (currentExpression.charAt(i) == '-')) {
                    if (CheckSign(currentExpression, i))
                    {
                        continue;
                    }
                    if (!Unar) {
                        s.push(currentExpression.charAt(i));
                        checker = checkPriority(s);
                        if (checker.length() != 0) {
                            output.add(checker);
                        }
                    } else {
                           if (currentExpression.charAt(i) == '-') {
                               output.add("0");
                               s.push(currentExpression.charAt(i));
                           }
                    }
                    continue;
                }
                if ((currentExpression.charAt(i) == '/') || (currentExpression.charAt(i) == '*')) {
                    s.push(currentExpression.charAt(i));
                    checker = checkPriority(s);
                    if (checker.length() != 0) {
                        output.add(checker);
                    }
                }
            }

            while (!output.isEmpty()) {
                outputString += output.remove();
            }
            while (!s.isEmpty()) {
                outputString += s.pop();
            }

            /////////////////////////////////////////// Dore+mifa*0sol-/-lyasi*+5*

            String str = "", variable = "";
            Stack<Double> CalcStack = new Stack<>();
            boolean ready = false;
            double operand1 = 0, operand2 = 0;
            int temp = 0;

            if (outputString.length() != 0) {
                for (i = 0; i < outputString.length(); i++) {
                    while ((outputString.charAt(i) >= '0') && (outputString.charAt(i) <= '9')) {
                        variable += outputString.charAt(i);
                        ready = true;
                        i++;
                    }
                    if (ready) {
                        CalcStack.push(Double.valueOf((variable)));
                        ready = false;
                        variable = "";
                    }
                    variable += outputString.charAt(i);
                    for (int j = 0; j < arithmeticExpression.getParameters().size(); j++) {
                        if (variable.equals(arithmeticExpression.getParameters().get(j).name)) {
                            CalcStack.push((double) arithmeticExpression.getParameters().get(j).value);
                            variable = "";
                            break;
                        }
                    }
                    if (variable.equals("")) {
                        continue;
                    }
                    if (((outputString.charAt(i) == '+') || (outputString.charAt(i) == '-') || (outputString.charAt(i) == '*') || (outputString.charAt(i) == '/')) && (variable.length() != 1)) {
                        System.out.println("ERROR: " + variable);
                    }
                    if ((variable.equals("+")) || (variable.equals("-")) || (variable.equals("*")) || (variable.equals("/"))) {
                        operand2 = CalcStack.pop();
                        operand1 = CalcStack.pop();
                        if (variable.equals("+")) {
                            operand1 = operand1 + operand2;
                        }
                        if (variable.equals("-")) {
                            operand1 = operand1 - operand2;
                        }
                        if (variable.equals("*")) {
                            operand1 = operand1 * operand2;
                        }
                        if (variable.equals("/")) {
                            operand1 = operand1 / operand2;
                        }
                        CalcStack.push(operand1);
                        variable = "";
                    }
                }
                results.add(String.valueOf(CalcStack.pop()));
            }
        }
        return results;
    }
}
