package com.myplatform.myplatform.embedded.security;

import org.apache.logging.log4j.util.Strings;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class TokenEncryptor {

    public static String encrypt(AuthenticationToken data, SecretKey key) throws Exception {

        String userData = data.getUserInfo().getUsername() + ";" + data.getUserInfo().getPassword();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(userData.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

}
