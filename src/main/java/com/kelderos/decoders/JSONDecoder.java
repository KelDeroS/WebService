package com.kelderos.decoders;

import com.kelderos.ArithmeticExpression;
import com.kelderos.Parameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONDecoder implements Decoder{
    int i = 0;
    String str = "";
    @Override
    public ArithmeticExpression readFile(byte[] file) throws FileNotFoundException
    {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        ArrayList<String> expressions = new ArrayList<>();
        ArrayList<Parameter> parameters = new ArrayList<>();
        Scanner scanner = new Scanner(new String(file));

        while (scanner.hasNextLine()) {
            str += scanner.nextLine();
        }

        while (i < str.length() - 1) {
            while (str.charAt(i) != '"' && i < str.length() - 1) {
                i++;
            }
            if (i == str.length() - 1)
            {
                break;
            }
            i++;
            String attribute = "";

            while (str.charAt(i) != '"') {
                attribute += str.charAt(i);
                i++;
            }
            i++;

            if (attribute.equals("expressions") || attribute.equals("Expressions")) {
                while (str.charAt(i) != '"' && str.charAt(i) != '[') {
                    i++;
                }
                if (str.charAt(i) == '"')
                {
                    expressions.add(getExpression());
                }
                else {
                    while (true) {
                        expressions.add(getExpression());
                        if (str.charAt(i) != ',')
                        {
                            break;
                        }
                    }
                }
                arithmeticExpression.setExpressions(expressions);
            } else if (attribute.equals("parameters") || attribute.equals("Parameters")) {
                while (true) {
                    parameters.add(getParameter());
                    if (str.charAt(i) != ',')
                    {
                        break;
                    }
                }
                arithmeticExpression.setParameters(parameters);
            }
        }
        return arithmeticExpression;
    }

    public String getExpression() {
        String expression = "";

        while (str.charAt(i) != '"') {
            i++;
        }
        i++;

        while (str.charAt(i) != '"') {
            if (str.charAt(i) != ' ') {
                expression += str.charAt(i);
            }
            i++;
        }
        i++;
        return expression;
    }
    public Parameter getParameter() {
        Parameter parameter = new Parameter();
        String parameterName = "";

        while (str.charAt(i) != '"') {
            i++;
        }
        i++;

        while (str.charAt(i) != '"') {
            parameterName += str.charAt(i);
            i++;
        }
        i++;
        parameter.name = parameterName;

        String value = "";

        while (str.charAt(i) != '"') {
            i++;
        }
        i++;

        while (str.charAt(i) != '"') {
            if (str.charAt(i) != ' ') {
                value += str.charAt(i);
            }
            i++;
        }
        i++;
        parameter.value = Integer.parseInt(value);
        return parameter;
    }
}
