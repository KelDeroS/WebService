package main.java;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.Parameter;
import main.java.com.kelderos.decoder.XMLDecoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File file = new File("input.xml");

        XMLDecoder xmlDecoder = new XMLDecoder();
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression();
        try {
            arithmeticExpression = xmlDecoder.readFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(arithmeticExpression.getExpression());
    }
}