package com.fitr.bntu.bookwebservice.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

}



