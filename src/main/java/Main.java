package main.java;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.Parameter;
import main.java.com.kelderos.decoder.JSONDecoder;
import main.java.com.kelderos.decoder.PlainTextDecoder;
import main.java.com.kelderos.decoder.XMLDecoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
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
    }
}