package com.kelderos.decoders;

import com.kelderos.ArithmeticExpression;

import java.io.File;
import java.io.FileNotFoundException;

public interface Decoder {
    ArithmeticExpression readFile(File file) throws FileNotFoundException;
}
