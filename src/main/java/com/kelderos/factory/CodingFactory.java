package com.kelderos.factory;

import com.kelderos.ArithmeticExpression;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface CodingFactory {
    ArithmeticExpression decode(byte[] file) throws FileNotFoundException;
    byte[] encode(ArrayList<String> results, String filename) throws IOException;
}
