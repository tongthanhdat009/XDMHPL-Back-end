package com.example.XDMHPL_Back_end.Controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.XDMHPL_Back_end.DTO.Users;
import com.example.XDMHPL_Back_end.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService usersService;
	
	@GetMapping
	public List<Users> getUsers() {
		return usersService.getAllUser();
	}
	
	@PostMapping("/create-user")
	public Users addUser(@RequestBody Users user) {
	    return usersService.createUser(
	        user.getFullName(), 
	        user.getUserName(), 
	        user.getPassword(),
	        user.getEmail(), 
	        user.getAvatar(), 
	        user.getPhoneNumber(), 
	        user.getDateOfBirth(), 
	        user.getGender(), 
	        user.getCoverPhotoURL(), 
	        user.getSessionID(), 
	        user.getRole()
	    );
	}
	
	
	
	@GetMapping("/{id}")
	public Users getUserById(@PathVariable Integer id) {
		return usersService.getUserById(id);
	}
}
