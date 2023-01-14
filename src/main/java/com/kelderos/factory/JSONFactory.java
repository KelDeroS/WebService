package com.kelderos.factory;

import com.kelderos.ArithmeticExpression;
import com.kelderos.decoders.Decoder;
import com.kelderos.decoders.JSONDecoder;
import com.kelderos.encoders.Encoder;
import com.kelderos.encoders.JSONEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class JSONFactory implements CodingFactory{
    @Override
    public ArithmeticExpression decode(File file) throws FileNotFoundException {
        Decoder decoder = new JSONDecoder();
        return decoder.readFile(file);
    }

    @Override
    public File encode(ArrayList<String> results, String filename) throws IOException {
        Encoder encoder = new JSONEncoder();
        return encoder.writeToFile(results, filename);
    }
}
