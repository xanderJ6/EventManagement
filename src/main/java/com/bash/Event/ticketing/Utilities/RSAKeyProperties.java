package com.bash.Event.ticketing.Utilities;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Configuration
public class RSAKeyProperties {
    public RSAPublicKey publicKey;
    public RSAPrivateKey privateKey;


    public RSAKeyProperties() throws NoSuchAlgorithmException {
        KeyPair keyPair = KeyGenerator.keyGenerator();

        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();

        this.publicKey = (RSAPublicKey) keyPair.getPublic();
    }
}
