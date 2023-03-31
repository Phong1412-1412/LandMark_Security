package com.phong1412.productsapi_security.exception;

public class BadException extends RuntimeException{
    public BadException(String messager) {
        super(messager);
    }
}
