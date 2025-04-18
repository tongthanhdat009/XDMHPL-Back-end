package com.example.XDMHPL_Back_end.DTO;

public class ErrorResponse {
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(String message) {
        this.message = message;
    }

    // getter & setter
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}