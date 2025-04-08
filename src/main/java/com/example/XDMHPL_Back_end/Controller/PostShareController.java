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

import com.example.XDMHPL_Back_end.DTO.PostShare;
import com.example.XDMHPL_Back_end.Services.PostShareService;

@RestController
@RequestMapping("/postshare")
public class PostShareController {
	@Autowired
	private PostShareService postShareService;
	
	@GetMapping
	public List<PostShare> getPostShare() {
		return postShareService.getAllPostShare();
	}
	
	@PostMapping
	public PostShare addPost(@RequestParam int originalPostID,
			@RequestParam int sharedByUserID,
			@RequestParam Date shareDate,
			@RequestParam String content) {
		return postShareService.createPostShare(originalPostID, sharedByUserID, shareDate, content);
	}
	
	@GetMapping("/{id}")
	public PostShare getPostShareByID(@PathVariable Integer id) {
		return postShareService.getPostShareByID(id);
	}

}
