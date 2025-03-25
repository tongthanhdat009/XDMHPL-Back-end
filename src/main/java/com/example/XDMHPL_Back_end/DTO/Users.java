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

	// Constructor rỗng bắt buộc cho JPA
	public Users() {}

	// Constructor đầy đủ
	public Users(String fullName, String userName, String password, String email, String avatar) {
		this.fullName = fullName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.avatar = avatar;
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
}
