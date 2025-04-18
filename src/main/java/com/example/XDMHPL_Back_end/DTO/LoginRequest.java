package com.example.XDMHPL_Back_end.DTO;

public class LoginRequest {
    private String userIdentifier;
    private String password;
    private String role;
    private String deviceInfo;
    
    // Getter và Setter
    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String identifier) {
        this.userIdentifier = identifier;
    }

    public String getRole() {
    	return role;
    }
    
    public String getDeviceInfo() {
    	return deviceInfo;
    }
    
    public void setDeviceInfo(String deviceInfo) {
    	this.deviceInfo = deviceInfo;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
