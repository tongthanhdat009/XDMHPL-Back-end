package com.example.XDMHPL_Back_end.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.XDMHPL_Back_end.DTO.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(Integer id);
}