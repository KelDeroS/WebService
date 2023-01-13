package main.java.com.kelderos.decoders;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.Parameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class XMLDecoder {
    int i = 0;
    String str = "";
    Stack<String> stack = new Stack<>();

    public ArithmeticExpression readFile(File file) throws FileNotFoundException
    {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        ArrayList<String> expressions = new ArrayList<>();
        ArrayList<Parameter> parameters = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            str += scanner.nextLine();
        }

        while (i < str.length() - 1) {
            while (str.charAt(i) != '<' && i < str.length() - 1) {
                i++;
            }
            i++;
            String firstWord = "", lastWord = "";

            if (str.charAt(i) == '/') {
                i++;
                while (str.charAt(i) != '>') {
                    lastWord += str.charAt(i);
                    i++;
                }
                if (!lastWord.equals(stack.pop())) {
                    System.out.println("Invalid stack");
                }
                continue;
            }
            while (str.charAt(i) != '>') {
                firstWord += str.charAt(i);
                i++;
            }
            i++;
            stack.push(firstWord);

            if (firstWord.equals("expressions") || firstWord.equals("Expressions")) {
                while (true) {
                    expressions.add(getExpression());
                    while (str.charAt(i) != '<') {
                        i++;
                    }
                    if (str.charAt(i + 1) == '/') {
                        break;
                    }
                }
                i = i + 2;
                while (str.charAt(i) != '>') {
                    lastWord += str.charAt(i);
                    i++;
                }
                if (!lastWord.equals(stack.pop())) {
                    System.out.println("Invalid stack");
                }
                arithmeticExpression.setExpressions(expressions);
            } else if (firstWord.equals("parameters") || firstWord.equals("Parameters")) {
                while (true) {
                    parameters.add(getParameter());
                    while (str.charAt(i) != '<') {
                        i++;
                    }
                    if (str.charAt(i + 1) == '/') {
                        break;
                    }
                }
                i = i + 2;
                while (str.charAt(i) != '>') {
                    lastWord += str.charAt(i);
                    i++;
                }
                if (!lastWord.equals(stack.pop())) {
                    System.out.println("Invalid stack");
                }
                arithmeticExpression.setParameters(parameters);
            }
        }
        return arithmeticExpression;
    }

    public String getExpression() {
        String expression = "", firstWord = "";

        while (str.charAt(i) != '<') {
            i++;
        }
        i++;

        while (str.charAt(i) != '>') {
            firstWord += str.charAt(i);
            i++;
        }
        i++;
        stack.push(firstWord);

        while (str.charAt(i) != '<') {
            if (str.charAt(i) != ' ') {
                expression += str.charAt(i);
            }
            i++;
        }
        i++;

        if (str.charAt(i) == '/') {
            i++;
            firstWord = "";

            while (str.charAt(i) != '>') {
                firstWord += str.charAt(i);
                i++;
            }
            if (!firstWord.equals(stack.pop())) {
                System.out.println("Invalid stack");;
            }
        } else {
            System.out.println("invalid format\n");
        }
        i++;

        return expression;
    }
    public Parameter getParameter() {
        Parameter parameter = new Parameter();
        String parameterName = "";

        while (str.charAt(i) != '<') {
            i++;
        }
        i++;

        while (str.charAt(i) != '>') {
            parameterName += str.charAt(i);
            i++;
        }
        i++;
        stack.push(parameterName);
        parameter.name = parameterName;

        String value = "";

        while (str.charAt(i) != '<') {
            if (str.charAt(i) != ' ') {
                value += str.charAt(i);
            }
            i++;
        }
        i++;

        parameter.value = Integer.parseInt(value);

        if (str.charAt(i) == '/') {
            i++;
            parameterName = "";

            while (str.charAt(i) != '>') {
                parameterName += str.charAt(i);
                i++;
            }
            if (!parameterName.equals(stack.pop())) {
                System.out.println("Invalid stack");;
            }
        } else {
            System.out.println("invalid format\n");
        }
        i++;

        return parameter;
    }
}