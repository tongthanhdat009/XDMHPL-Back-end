package com.example.XDMHPL_Back_end.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.XDMHPL_Back_end.DTO.PostMedia;
import com.example.XDMHPL_Back_end.Services.PostMediaService;

@RestController
@RequestMapping("/postmedia")
public class PostMediaController {
	@Autowired
	private PostMediaService postMediaService;
	
	@GetMapping
	public List<PostMedia> getPostMedia() {
		return postMediaService.getAllPostMedia();
	}
	
	@PostMapping
	public PostMedia addPost(@RequestParam String type,
			@RequestParam String mediaURL,
			@RequestParam int postID) {
		return postMediaService.createPostMedia(type, mediaURL, postID);
	}
	
	@GetMapping("/{id}")
	public PostMedia getPostMediaByID(@PathVariable Integer id) {
		return postMediaService.getPostMediaByID(id);
	}

}
