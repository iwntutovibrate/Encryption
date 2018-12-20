package com.example.demo.cryp;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256 {

    private static volatile AES256 INSTANCE;

    final static String secretKey = "12345678901234561234567890123456";
    static String IV = "";

    public static AES256 getInstance() {

        if (INSTANCE == null) {
            synchronized (AES256.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AES256();
                }
            }
        }
        return INSTANCE;
    }

    private AES256() {
        IV = secretKey.substring(0, 16);
    }


    /**
     * @since	2018.12.20
     * @author	iwntutovibrate
     * @param	str - String
     * @return	AES256 encode.String
     */
    public static String encode(String str) {
        String encodedString = null;
        try {

            byte[] keyData = secretKey.getBytes();
            SecretKey secureKey = new SecretKeySpec(keyData, "AES");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

            byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
            encodedString = new String(Base64.encodeBase64(encrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    /**
     * @since	2018.12.20
     * @author	iwntutovibrate
     * @param	str - String
     * @return	AES256 decode.String
     */
    public static String decode(String str) {
        String decodedString = null;
        try {

            byte[] keyData = secretKey.getBytes();
            SecretKey secureKey = new SecretKeySpec(keyData, "AES");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));

            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            decodedString = new String(c.doFinal(byteStr), "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedString;
    }

}
