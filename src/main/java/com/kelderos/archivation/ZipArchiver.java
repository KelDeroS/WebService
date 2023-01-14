package com.kelderos.archivation;

import java.io.ByteArrayOutputStream;

import java.util.zip.ZipEntry;
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
}
