package com.example.XDMHPL_Back_end.DTO;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userID;

	@Column(name = "`FullName`", nullable = true, unique = false)
	private String fullName;

	@Column(name = "UserName", nullable = true, unique = true)
	private String userName;

	@Column(name = "Email", nullable = true, unique = true)
	private String email;

	@Column(name = "Password", nullable = true)
	private String password;

	@Column(name = "AvatarURL", nullable = true)
	private String avatar;
	
	@Column(name = "PhoneNumber", nullable = true)
	private String phoneNumber;
	
	@Column(name = "DateOfBirth", nullable = true)
	private LocalDate dateOfBirth;
	
	@Column(name = "Gender", nullable = true)
	private String gender;
	
	@Column(name = "CoverPhotoURL", nullable = true)
	private String coverPhotoURL;
	
	@Column(name = "SessionID", nullable = true)
	private String sessionID;

	@Column(name ="Bio", nullable = true)
	private String Bio;
	
	@Column(name = "Role", nullable = true)
	private String role;
	
	// Constructor rỗng bắt buộc cho JPA
	public Users() {}

	// Constructor đầy đủ
	public Users(String fullName, String userName, String password, String email, String avatar, String phoneNumber, LocalDate dateOfBirth, String gender
			, String coverPhotoURL, String sessionID,String Bio, String role) {
		this.fullName = fullName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.Bio = Bio;
		this.avatar = avatar;
		this.sessionID = sessionID;
		this.coverPhotoURL = coverPhotoURL;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}

	// Getter và Setter đúng
	public String getBio() { return Bio; }
	public void setBio(String bio) { this.Bio = bio; }

	public int getUserID() { return userID; }
	public void setUserID(int id) { this.userID = id; }
	
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	
	public LocalDate  getDateOfBirth() {return dateOfBirth;}
	public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}
	
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}
	
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
