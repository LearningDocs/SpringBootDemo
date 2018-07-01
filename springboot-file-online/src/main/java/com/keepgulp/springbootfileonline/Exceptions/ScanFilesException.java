package com.keepgulp.springbootfileonline.Exceptions;

public class ScanFilesException extends RuntimeException {

    public ScanFilesException() {
    }

    public ScanFilesException(String message) {
        super(message);
    }

    public ScanFilesException(String message, Throwable cause) {
        super(message, cause);
    }
}
