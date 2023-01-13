package main.java.com.kelderos.encoders;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLEncoder {
    public void writeToFile(ArrayList<String> results) throws IOException {
        new FileOutputStream("results.xml").close();
        FileWriter fileWriter = new FileWriter("results.xml", true);
        fileWriter.write("<results>\n");
        for (int i = 0; i < results.size(); i++) {
            fileWriter.write("\t<result>" + results.get(i) + "</result>\n");
        }
        fileWriter.write("</results>");
        fileWriter.close();
    }
}
