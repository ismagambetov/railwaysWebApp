package com.epam.ism.utils;

public class DateTimeUtilException extends RuntimeException {

    public DateTimeUtilException(String message) {
        super(message);
    }

    public DateTimeUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateTimeUtilException(Throwable cause) {
        super(cause);
    }
}
