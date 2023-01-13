package main.java;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.calculator.Calculator;
import main.java.com.kelderos.decoders.JSONDecoder;
import main.java.com.kelderos.decoders.PlainTextDecoder;
import main.java.com.kelderos.decoders.XMLDecoder;
import main.java.com.kelderos.encoders.JSONEncoder;
import main.java.com.kelderos.encoders.PlainTextEncoder;
import main.java.com.kelderos.encoders.XMLEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        File file1 = new File("input.xml");
        File file2 = new File("input.json");
        File file3 = new File("input.txt");

        XMLDecoder xmlDecoder = new XMLDecoder();
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        try {
            arithmeticExpression = xmlDecoder.readFile(file1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < arithmeticExpression.getExpressions().size(); i++) {
            System.out.println(arithmeticExpression.getExpressions().get(i));
        }

        for (int i = 0; i < arithmeticExpression.getParameters().size(); i++) {
            System.out.println("Name: " + arithmeticExpression.getParameters().get(i).name);
            System.out.println("Value: " + arithmeticExpression.getParameters().get(i).value);
        }
        System.out.println();

        JSONDecoder jsonDecoder = new JSONDecoder();
        try {
            arithmeticExpression = jsonDecoder.readFile(file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < arithmeticExpression.getExpressions().size(); i++) {
            System.out.println(arithmeticExpression.getExpressions().get(i));
        }

        for (int i = 0; i < arithmeticExpression.getParameters().size(); i++) {
            System.out.println("Name: " + arithmeticExpression.getParameters().get(i).name);
            System.out.println("Value: " + arithmeticExpression.getParameters().get(i).value);
        }
        System.out.println();

        PlainTextDecoder plainTextDecoder = new PlainTextDecoder();
        try {
            arithmeticExpression = plainTextDecoder.readFile(file3);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < arithmeticExpression.getExpressions().size(); i++) {
            System.out.println(arithmeticExpression.getExpressions().get(i));
        }

        for (int i = 0; i < arithmeticExpression.getParameters().size(); i++) {
            System.out.println("Name: " +  arithmeticExpression.getParameters().get(i).name);
            System.out.println("Value: " + arithmeticExpression.getParameters().get(i).value);
        }
        System.out.println();

        Calculator calculator = new Calculator();
        ArrayList<String> results = calculator.calculate(arithmeticExpression);
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }

        XMLEncoder xmlEncoder = new XMLEncoder();
        xmlEncoder.writeToFile(results);

        JSONEncoder jsonEncoder = new JSONEncoder();
        jsonEncoder.writeToFile(results);

        PlainTextEncoder plainTextEncoder = new PlainTextEncoder();
        plainTextEncoder.writeToFile(results);
    }
}