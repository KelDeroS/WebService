package com.kelderos.encoders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class XMLEncoder implements Encoder{
    @Override
    public byte[] writeToFile(ArrayList<String> results, String filename) throws IOException {
        File file = new File(filename);
        new FileOutputStream(file).close();
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("<results>\n");
        for (int i = 0; i < results.size(); i++) {
            fileWriter.write("\t<result>" + results.get(i) + "</result>\n");
        }
        fileWriter.write("</results>");
        fileWriter.close();
        return Files.readAllBytes(file.toPath());
    }
}
