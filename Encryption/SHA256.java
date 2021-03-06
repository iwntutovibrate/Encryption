package com.example.demo.cryp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    /**
     * @Date	2018.12.20
     * @param	str - String
     * @return	SHA256 encode.String
     */
    public static String encode(String str) {

        String SHA = "";

        try {

            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());

            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }

}
