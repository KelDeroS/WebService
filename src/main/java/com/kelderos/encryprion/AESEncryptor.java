package com.kelderos.encryprion;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;


public class AESEncryptor {
    private static final String KEY = "1234567812345678";

    public static byte[] encrypt(byte[] message) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }
}
