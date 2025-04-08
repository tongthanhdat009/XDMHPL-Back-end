package com.example.XDMHPL_Back_end.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.XDMHPL_Back_end.DTO.Post;
import com.example.XDMHPL_Back_end.Services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<Post> getCustomers() {
		return postService.getAllPost();
	}
	
	@PostMapping
	public Post addPost(@RequestParam Date creationDate,
			@RequestParam String type,
			@RequestParam int userID,
			@RequestParam String content,
			@RequestParam int priorityScore) {
		return postService.createPost(creationDate, type, userID, content, priorityScore);
	}
	
	@GetMapping("/{id}")
	public Post getPostByID(@PathVariable Integer id) {
		return postService.getPostByID(id);
	}

}
