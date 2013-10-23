package com.pocketsunited.mangopayapiwrapper.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class PrivateKeyFactory {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private File privateKeyFile;

    public void setPrivateKeyClasspathFilename(String privateKeyFilename) {
        try {
            privateKeyFile = new File(getClass().getClassLoader().getResource(privateKeyFilename).toURI());
        }
        catch (URISyntaxException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("",e);
        }
    }

    public void setPrivateKeyURI(String privateKeyURI) {
        this.privateKeyFile = new File(privateKeyURI);
    }

    public PrivateKey getPrivateKey() {
        Exception exception;
        try {
            byte[] bytes = new byte[(int)privateKeyFile.length()];
            new FileInputStream(privateKeyFile).read(bytes);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(bytes));
            return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        }
        catch (NoSuchAlgorithmException e) {
            exception = e;
        }
        catch (IOException e) {
            exception = e;
        }
        catch (InvalidKeySpecException e) {
            exception = e;
        }
        logger.error(exception.getMessage());
        throw new RuntimeException("",exception);
    }
}
