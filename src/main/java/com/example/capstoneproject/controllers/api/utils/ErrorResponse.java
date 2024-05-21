package com.example.capstoneproject.controllers.api.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private String message;

    public ErrorResponse(@JsonProperty("message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
