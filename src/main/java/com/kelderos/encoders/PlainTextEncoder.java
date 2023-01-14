package com.kelderos.encoders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlainTextEncoder implements Encoder{
    @Override
    public File writeToFile(ArrayList<String> results, String filename) throws IOException {
        new FileOutputStream(filename).close();
        FileWriter fileWriter = new FileWriter(filename, true);
        for (int i = 0; i < results.size(); i++) {
            fileWriter.write("| " + results.get(i) + " |\n");
        }
        fileWriter.close();
        return new File("results.txt");
    }
}
