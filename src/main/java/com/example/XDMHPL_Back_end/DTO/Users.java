package com.example.XDMHPL_Back_end.DTO;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;

	@Column(name = "`FullName`", nullable = false, unique = false)
	private String fullName;

	@Column(name = "UserName", nullable = false, unique = true)
	private String userName;

	@Column(name = "Email", nullable = false, unique = true)
	private String email;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "AvatarURL", nullable = false)
	private String avatar;
	
	@Column(name = "CoverPhotoURL", nullable = false)
	private String coverPhotoURL;
	
	@Column(name = "SessionID", nullable = false)
	private String sessionID;
	
	@Column(name = "Role", nullable = false)
	private String role;
	// Constructor rỗng bắt buộc cho JPA
	public Users() {}

	// Constructor đầy đủ
	public Users(String fullName, String userName, String password, String email, String avatar, String coverPhotoURL, String sessionID, String role) {
		this.fullName = fullName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.avatar = avatar;
		this.sessionID = sessionID;
		this.coverPhotoURL = coverPhotoURL;
		this.role = role;
	}

	// Getter và Setter đúng
	public int getUserID() { return userID; }
	public void setUserID(int id) { this.userID = id; }

	public String getFullName() { return fullName; }
	public void setFullName(String name) { this.fullName = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getAvatar() { return avatar; }
	public void setAvatar(String avatar) { this.avatar = avatar; }
	
	public String getCoverPhotoURL() { return coverPhotoURL;}
	public void setCoverPhotoURL(String coverPhotoURL) { this.coverPhotoURL = coverPhotoURL;}

	public String getSessionID() { return sessionID;}
	public void setSessionID(String sessionID) { this.sessionID = sessionID;}
	
	public String getRole() { return role;}
	public void setRole(String role) { this.role = role;}
	
}
