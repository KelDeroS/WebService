package com.kelderos.encoders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONEncoder implements Encoder{
    @Override
    public File writeToFile(ArrayList<String> results, String filename) throws IOException {
        new FileOutputStream(filename).close();
        FileWriter fileWriter = new FileWriter(filename, true);
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
        return new File("results.json");
    }

}
