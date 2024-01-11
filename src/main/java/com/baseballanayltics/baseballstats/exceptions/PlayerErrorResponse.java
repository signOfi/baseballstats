package com.baseballanayltics.baseballstats.exceptions;

import lombok.Data;

@Data
public class PlayerErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

}
