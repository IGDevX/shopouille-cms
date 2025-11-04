package org.shopouille.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int statusCode;

    public static ErrorResponse fromMessage(String message, int statusCode) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(message);
        response.setStatusCode(statusCode);
        return response;
    }
}

