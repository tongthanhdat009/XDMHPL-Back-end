package com.example.XDMHPL_Back_end.DTO;

public class LoginResponse {
    private String sessionId;
    private int userId;
    private String username;
    private String role;

    public LoginResponse(String sessionId, int userId, String username, String role) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // getters & setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}