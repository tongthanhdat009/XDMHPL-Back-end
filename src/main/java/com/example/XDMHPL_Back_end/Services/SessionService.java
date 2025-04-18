package com.example.XDMHPL_Back_end.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XDMHPL_Back_end.DTO.Session;
import com.example.XDMHPL_Back_end.Repositories.SessionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public String createSession(int userID, String deviceInfo) {
        String sessionId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(
            sessionId,
            userID,
            now,
            now.plusHours(2),
            deviceInfo
        );
        sessionRepository.save(session);
        return sessionId;
    }

    public void logout(String sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}