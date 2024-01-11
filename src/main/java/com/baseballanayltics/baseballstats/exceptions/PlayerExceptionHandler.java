package com.baseballanayltics.baseballstats.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlayerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PlayerErrorResponse> playerNotFound (PlayerNotFoundException exception) {
        /* This handles student not found exceptions */

        /* Need to create a player error response */
        PlayerErrorResponse playerErrorResponse = new PlayerErrorResponse();
        playerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        playerErrorResponse.setMessage(exception.getMessage());
        playerErrorResponse.setTimeStamp(System.currentTimeMillis());

        /* Return Response entity */
        return new ResponseEntity<>(playerErrorResponse, HttpStatus.NOT_FOUND);
    }

}
