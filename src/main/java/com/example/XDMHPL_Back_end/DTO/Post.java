package com.example.XDMHPL_Back_end.DTO;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postID;

	@Column(name = "CreationDate", nullable = false, unique = false)
	private Date creationDate;

	@Column(name = "Type", nullable = false, unique = false)
	private String type;

	@Column(name = "UserID", nullable = false, unique = false)
	private int userID;

	@Column(name = "Content", nullable = false)
	private String content;
    
    @Column(name = "PriorityScore", nullable = false, unique = false)
    private int priorityScore;
    
	// Constructor rỗng bắt buộc cho JPA
	public Post() {}

	// Constructor đầy đủ
	public Post(Date creationDate, String type, int userID, String content, int priorityScore) {
        this.creationDate = creationDate;
        this.type = type;
        this.userID = userID;
        this.content = content;
        this.priorityScore = priorityScore;
    }
    
    // Getters
    public int getPostID() {
        return postID;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public String getType() {
        return type;
    }
    
    public int getUserID() {
        return userID;
    }
    
    public String getContent() {
        return content;
    }
    
    public int getPriorityScore() {
        return priorityScore;
    }
    
    // Setters
    public void setPostID(int postID) {
        this.postID = postID;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }
}
