package com.kelderos.archivation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {
    public File archive(File inputFile, File outputFile) throws IOException {

        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine())
        {
            sb.append(scanner.nextLine());
            sb.append("\n");
        }

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        ZipEntry entry = new ZipEntry(inputFile.getName());

        out.putNextEntry(entry);
        byte[] data = sb.toString().getBytes();
        out.write(data);
        out.closeEntry();
        out.close();

        return outputFile;
    }
}
