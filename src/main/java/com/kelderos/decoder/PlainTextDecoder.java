package main.java.com.kelderos.decoder;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.Parameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlainTextDecoder {
    public ArithmeticExpression readFile(File file) throws FileNotFoundException {
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        ArrayList<String> expressions = new ArrayList<>();
        ArrayList<Parameter> parameters = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        int lastCommand = 0;
        while (scanner.hasNextLine())
        {
            String str = scanner.nextLine(), clearStr = "";
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ') {
                    clearStr += str.charAt(i);
                }
            }
            if (clearStr.equals("expressions:") || clearStr.equals("Expressions:"))
            {
                lastCommand = 1;
                continue;
            }
            else if (clearStr.equals("parameters:") || clearStr.equals("Parameters:")) {
                lastCommand = 2;
                continue;
            }

            if (lastCommand == 1)
            {
                expressions.add(clearStr);
            }
            else if (lastCommand == 2)
            {
                Parameter parameter = new Parameter();
                String parameterName = "", value = "";
                boolean lever = false;
                for (int i = 0; i < clearStr.length(); i++) {
                    if (clearStr.charAt(i) == '=')
                    {
                        lever = true;
                        continue;
                    }

                    if (!lever)
                    {
                        parameterName += clearStr.charAt(i);
                    }
                    else {
                        value += clearStr.charAt(i);
                    }
                }
                parameter.name = parameterName;
                parameter.value = Integer.parseInt(value);
                parameters.add(parameter);
            }
            else
            {
                System.out.println("Invalid input file format");
            }
        }
        arithmeticExpression.setExpressions(expressions);
        arithmeticExpression.setParameters(parameters);
        return arithmeticExpression;
    }
}
