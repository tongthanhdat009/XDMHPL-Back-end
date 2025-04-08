package com.example.XDMHPL_Back_end.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XDMHPL_Back_end.Repositories.PostRepository;
import com.example.XDMHPL_Back_end.DTO.Post;


@Service
public class PostService {
	@Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post createPost(Date creationDate, String type, int userID, String content, int priorityScore) {
    	Post post = new Post(creationDate, type, userID, content, priorityScore);
        return postRepository.save(post);
    }
    
    public Post getPostByID(Integer id) {
        return postRepository.findById(id).orElse(null);
    }
}
