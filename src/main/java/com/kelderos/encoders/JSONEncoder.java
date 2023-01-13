package main.java.com.kelderos.encoders;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONEncoder {
    public void writeToFile(ArrayList<String> results) throws IOException {
        new FileOutputStream("results.json").close();
        FileWriter fileWriter = new FileWriter("results.json", true);
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
    }
}