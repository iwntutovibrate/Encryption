package com.example.demo.cryp;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES128 {

    private static final String CRYPTO_KEY = "nothing.gonna.do";

    /**
     * @Date	2018.12.20
     * @param	str - String
     * @return	AES128 encrypt.String
     */
    public static String encrypt(String str) {

        try {

            if (str == null || str.isEmpty()) {
                return "";
            }

            IvParameterSpec ivspec = new IvParameterSpec(CRYPTO_KEY.getBytes());
            Key keySpec = new SecretKeySpec(CRYPTO_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivspec);

            return new String(ByteUtils.toHexString(cipher.doFinal(str.getBytes())));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Date	2018.12.20
     * @param	enc - String
     * @return	AES128 Decrypt.String
     */
    public static String decrypt(String enc) {
        try {

            if (enc == null || enc.isEmpty()) {
                return "";
            }

            IvParameterSpec ivspec = new IvParameterSpec(CRYPTO_KEY.getBytes());
            Key keyspec = new SecretKeySpec(CRYPTO_KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            return new String(cipher.doFinal(ByteUtils.toBytesFromHexString(enc)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
