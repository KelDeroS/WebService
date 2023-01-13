package main.java.com.kelderos.encoders;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XMLEncoder implements Encoder{
    @Override
    public File writeToFile(ArrayList<String> results, String filename) throws IOException {
        new FileOutputStream(filename).close();
        FileWriter fileWriter = new FileWriter(filename, true);
        fileWriter.write("<results>\n");
        for (int i = 0; i < results.size(); i++) {
            fileWriter.write("\t<result>" + results.get(i) + "</result>\n");
        }
        fileWriter.write("</results>");
        fileWriter.close();
        return new File("results.xml");
    }
}
