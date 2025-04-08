package com.example.XDMHPL_Back_end.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.XDMHPL_Back_end.DTO.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Integer id);
}