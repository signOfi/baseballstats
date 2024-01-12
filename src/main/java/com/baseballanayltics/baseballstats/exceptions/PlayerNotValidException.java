package com.baseballanayltics.baseballstats.exceptions;

public class PlayerNotValidException extends RuntimeException{

    public PlayerNotValidException(String message) {
        super(message);
    }

    public PlayerNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerNotValidException(Throwable cause) {
        super(cause);
    }

}
