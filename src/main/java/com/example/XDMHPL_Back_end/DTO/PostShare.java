package com.example.XDMHPL_Back_end.DTO;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "postshare")
public class PostShare {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shareID;

	@Column(name = "OriginalPostID", nullable = false, unique = false)
	private int originalPostID;

	@Column(name = "SharedByUserID", nullable = false, unique = false)
	private int sharedByUserID ;

	@Column(name = "ShareDate", nullable = false, unique = false)
	private Date shareDate;

	@Column(name = "Content", nullable = false)
	private String content;
    
	// Constructor rỗng bắt buộc cho JPA
	public PostShare() {}

	// Constructor đầy đủ
	public PostShare(int originalPostID, int sharedByUserID, Date shareDate, String content) {
        this.originalPostID = originalPostID;
        this.sharedByUserID = sharedByUserID;
        this.shareDate = shareDate;
        this.content = content;
    }
    
    // Getters
    public int getShareID() {
        return shareID;
    }
    public int getOriginalPostID() {
        return originalPostID;
    }
    public int getSharedByUserID() {
        return sharedByUserID;
    }
    public Date getShareDate() {
        return shareDate;
    }
    public String getContent() {
        return content;
    }
    
    //setters
    public void setShareID(int shareID) {
        this.shareID = shareID;
    }
    public void setOriginalPostID(int originalPostID) {
        this.originalPostID = originalPostID;
    }
    public void setSharedByUserID(int sharedByUserID) {
        this.sharedByUserID = sharedByUserID;
    }
    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}
