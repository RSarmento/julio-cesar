package com.codenation.exception;

public class CipherException extends RuntimeException {
    public CipherException(String s) { super(s); }
    public CipherException(String s, Throwable t) { super(s, t); }

}
