package com.bash.Event.ticketing.Utilities;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {
    public static KeyPair keyGenerator() throws NoSuchAlgorithmException {

        KeyPair keypair;

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        keypair = keyPairGenerator.generateKeyPair();

        return keypair;
    };
}
