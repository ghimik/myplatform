package com.myplatform.myplatform.embedded.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class TokenDecryptor {

    public static AuthenticationToken decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        String[] data = new String(decryptedBytes).split(";");
        return () -> new UserInfo() {
            @Override
            public String getUsername() {
                return data[0];
            }

            @Override
            public String getPassword() {
                return data[1];
            }
        };
    }

}
