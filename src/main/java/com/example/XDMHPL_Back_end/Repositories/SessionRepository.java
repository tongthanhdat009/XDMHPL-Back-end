package com.example.XDMHPL_Back_end.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.XDMHPL_Back_end.DTO.Session;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, String> {
    Optional<Session> findBySessionIDAndExpiresAtAfter(String sessionID, LocalDateTime now);
}