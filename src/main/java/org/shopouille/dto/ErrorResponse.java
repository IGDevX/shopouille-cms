package org.shopouille.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
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
