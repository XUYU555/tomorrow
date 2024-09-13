package com.xxyy.quandaiban1.infrastructure;

/**
 * @author xy
 * @date 2023-09-12 20:39
 */
public class AppException extends RuntimeException{
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}