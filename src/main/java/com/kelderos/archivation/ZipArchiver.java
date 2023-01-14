package com.kelderos.archivation;

import com.kelderos.webservice.FileType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {
    public byte[] zip(String filename, byte[] data) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zout = new ZipOutputStream(byteArrayOutputStream);
            ZipEntry zipEntry = new ZipEntry(filename);
            zout.putNextEntry(zipEntry);
            zout.write(data);
            zout.closeEntry();
            zout.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public FileType unzip(byte[] data) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(data));
            ZipEntry zipEntry;

            if ((zipEntry = zipInputStream.getNextEntry()) != null) {

                return new FileType(zipEntry.getName(), zipInputStream.readAllBytes());
            } else
                throw new IllegalArgumentException("Zip contains no files");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
