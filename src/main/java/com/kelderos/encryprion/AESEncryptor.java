package com.kelderos.encryprion;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class AESEncryptor {
    public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv, File inputFile, File outputFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        FileOutputStream outputStream = new FileOutputStream(outputFile);

        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine())
        {
            sb.append(scanner.nextLine());
            sb.append("\n");
        }
        byte[] cipherText = cipher.doFinal(sb.toString().getBytes());
        if (cipherText != null) {
            outputStream.write(cipherText);
        }
        scanner.close();
        outputStream.close();
    }
}
