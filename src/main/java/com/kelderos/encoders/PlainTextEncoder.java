package main.java.com.kelderos.encoders;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlainTextEncoder {
    public void writeToFile(ArrayList<String> results) throws IOException {
        new FileOutputStream("results.txt").close();
        FileWriter fileWriter = new FileWriter("results.txt", true);
        fileWriter.write("results:\n");
        for (int i = 0; i < results.size(); i++) {
            fileWriter.write("\t" + results.get(i) + "\n");
        }
        fileWriter.close();
    }
}
