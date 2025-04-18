package com.example.XDMHPL_Back_end.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Sessions")
public class Session {
    @Id
    private String sessionID;
    private int userID;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String deviceInfo;

    public Session() {}

    public Session(String sessionID, int userID, LocalDateTime createdAt,
                   LocalDateTime expiresAt, String deviceInfo) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.deviceInfo = deviceInfo;
    }

    // getters & setters
    public String getSessionID() { return sessionID; }
    public void setSessionID(String sessionID) { this.sessionID = sessionID; }
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public String getDeviceInfo() { return deviceInfo; }
    public void setDeviceInfo(String deviceInfo) { this.deviceInfo = deviceInfo; }
}