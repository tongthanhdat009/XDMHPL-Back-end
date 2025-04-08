package com.example.XDMHPL_Back_end.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XDMHPL_Back_end.Repositories.PostMediaRepository;
import com.example.XDMHPL_Back_end.DTO.PostMedia;


@Service
public class PostMediaService {
	@Autowired
    private PostMediaRepository postMediaRepository;

    public List<PostMedia> getAllPostMedia() {
        return postMediaRepository.findAll();
    }

    public PostMedia createPostMedia(String type, String mediaURL, int postID) {
    	PostMedia postMedia = new PostMedia( type, mediaURL, postID);
        return postMediaRepository.save(postMedia);
    }
    
    public PostMedia getPostMediaByID(Integer id) {
        return postMediaRepository.findById(id).orElse(null);
    }
}
