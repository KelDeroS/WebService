package main.java.com.kelderos.factory;

import main.java.com.kelderos.ArithmeticExpression;
import main.java.com.kelderos.decoders.Decoder;
import main.java.com.kelderos.decoders.PlainTextDecoder;
import main.java.com.kelderos.decoders.XMLDecoder;
import main.java.com.kelderos.encoders.Encoder;
import main.java.com.kelderos.encoders.PlainTextEncoder;
import main.java.com.kelderos.encoders.XMLEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PlainTextFactory implements CodingFactory{
    @Override
    public ArithmeticExpression decode(File file) throws FileNotFoundException {
        Decoder decoder = new PlainTextDecoder();
        return decoder.readFile(file);
    }

    @Override
    public File encode(ArrayList<String> results, String filename) throws IOException {
        Encoder encoder = new PlainTextEncoder();
        return encoder.writeToFile(results, filename);
    }
}
