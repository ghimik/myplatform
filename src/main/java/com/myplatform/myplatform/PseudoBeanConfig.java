package com.myplatform.myplatform;

import com.myplatform.myplatform.embedded.security.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class PseudoBeanConfig {

    public static final SecurityContext securityContextHolder;

    public static final StaticUserDataService userDataService;

    public static final SecretKey secretKey;

    public static final AuthorizationTokenFactory authorizationTokenFactory;

    static {
        securityContextHolder = new SecurityContextImpl();
        userDataService = new StaticUserDataService();
        userDataService
                .addUser("Alex", "root")
                .addUser("Gosha", "grisha");


        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        keyGen.init(128);
        secretKey = keyGen.generateKey();

        authorizationTokenFactory = new AuthorizationTokenFactory(secretKey);
    }

}
