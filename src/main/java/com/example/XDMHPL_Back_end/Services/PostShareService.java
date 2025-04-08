package com.example.XDMHPL_Back_end.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XDMHPL_Back_end.Repositories.PostShareRepository;
import com.example.XDMHPL_Back_end.DTO.PostShare;


@Service
public class PostShareService {
	@Autowired
    private PostShareRepository postShareRepository;

    public List<PostShare> getAllPostShare() {
        return postShareRepository.findAll();
    }

    public PostShare createPostShare(int originalPostID, int sharedByUserID, Date shareDate, String content) {
    	PostShare postShare = new PostShare( originalPostID, sharedByUserID, shareDate, content);
        return postShareRepository.save(postShare);
    }
    
    public PostShare getPostShareByID(Integer id) {
        return postShareRepository.findById(id).orElse(null);
    }
}
