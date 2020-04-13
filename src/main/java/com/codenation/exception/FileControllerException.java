package com.codenation.exception;

public class FileControllerException extends RuntimeException {
    public FileControllerException(String s) { super(s); }
    public FileControllerException(String s, Throwable t) { super(s, t); }

}
