package com.kelderos.factory;

import com.kelderos.ArithmeticExpression;
import com.kelderos.decoders.Decoder;
import com.kelderos.decoders.PlainTextDecoder;
import com.kelderos.decoders.XMLDecoder;
import com.kelderos.encoders.Encoder;
import com.kelderos.encoders.PlainTextEncoder;
import com.kelderos.encoders.XMLEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PlainTextFactory implements CodingFactory{
    @Override
    public ArithmeticExpression decode(byte[] file) throws FileNotFoundException {
        Decoder decoder = new PlainTextDecoder();
        return decoder.readFile(file);
    }

    @Override
    public byte[] encode(ArrayList<String> results, String filename) throws IOException {
        Encoder encoder = new PlainTextEncoder();
        return encoder.writeToFile(results, filename);
    }
}
