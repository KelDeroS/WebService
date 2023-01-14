package com.kelderos.encoders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class JSONEncoder implements Encoder{
    @Override
    public byte[] writeToFile(ArrayList<String> results, String filename) throws IOException {
        File file = new File(filename);
        new FileOutputStream(file).close();
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("{\n");
        fileWriter.write("\t" + '"' + "results" + '"' + ": [\n");
        for (int i = 0; i < results.size(); i++) {
            fileWriter.write("\t\t" + results.get(i));
            if (i < results.size() - 1)
            {
                fileWriter.write(",\n");
            }
            else
            {
                fileWriter.write("\n");
            }
        }
        fileWriter.write("\t]\n");
        fileWriter.write("}");
        fileWriter.close();
        return Files.readAllBytes(file.toPath());
    }

}
