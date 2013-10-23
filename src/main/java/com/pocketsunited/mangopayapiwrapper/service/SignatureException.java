package com.pocketsunited.mangopayapiwrapper.service;

/**
 * @author Michael Duergner <michael@pocketsunited.com>
 */
public class SignatureException extends Exception {

    public SignatureException() {}

    public SignatureException(String s) {
        super(s);
    }

    public SignatureException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SignatureException(Throwable throwable) {
        super(throwable);
    }
}
