package com.example.XDMHPL_Back_end.DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "postmedia")
public class PostMedia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postMediaID;

	@Column(name = "Type", nullable = false, unique = false)
	private String type;

	@Column(name = "MediaURL", nullable = false, unique = false)
	private String mediaURL;

	@Column(name = "PostID", nullable = false)
	private int postID;
    
	public PostMedia() {}

	// Constructor đầy đủ
	public PostMedia(String type, String mediaURL, int postID) {
        this.type = type;
        this.mediaURL = mediaURL;
        this.postID = postID;
	}
    
	// Getters
	public int getPostMediaID() {
		return postMediaID;
	}
	
	public String getType() {
		return type;
	}
	
	public String getMediaURL() {
		return mediaURL;
	}
	
	public int getPostID() {
		return postID;
	}
	
	// Setters
	public void setPostMediaID(int postMediaID) {
		this.postMediaID = postMediaID;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}
	
	public void setPostID(int postID) {
		this.postID = postID;
	}
}
