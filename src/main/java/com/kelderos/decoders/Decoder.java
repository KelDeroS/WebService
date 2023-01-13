package main.java.com.kelderos.decoders;

import main.java.com.kelderos.ArithmeticExpression;

import java.io.File;
import java.io.FileNotFoundException;

public interface Decoder {
    ArithmeticExpression readFile(File file) throws FileNotFoundException;
}
