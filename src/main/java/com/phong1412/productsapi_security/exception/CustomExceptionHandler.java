package com.phong1412.productsapi_security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException ms, WebRequest rq) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, ms.getMessage());
    }

    @ExceptionHandler(BadException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handlerUnprocessableException(BadException ms, WebRequest rq) {
        return new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, ms.getMessage());
    }
}
