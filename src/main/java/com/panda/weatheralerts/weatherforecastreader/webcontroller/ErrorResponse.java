package com.panda.weatheralerts.weatherforecastreader.webcontroller;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
}
