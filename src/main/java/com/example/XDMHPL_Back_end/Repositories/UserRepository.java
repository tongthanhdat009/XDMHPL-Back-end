package com.example.XDMHPL_Back_end.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.XDMHPL_Back_end.DTO.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(Integer id);
    Users findByEmail(String email);
    Users findByUserName(String userName);
    Users findByPhoneNumber(String phoneNumber);
}