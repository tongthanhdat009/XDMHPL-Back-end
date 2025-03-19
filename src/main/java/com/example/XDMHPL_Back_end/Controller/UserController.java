package com.example.XDMHPL_Back_end.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Users> getCustomers() {
		return usersService.getAllCustomers();
	}
	
	@PostMapping
	public Users addUser(@RequestParam String fullName, @RequestParam String userName, @RequestParam String password, @RequestParam String email, @RequestParam String avatar) {
		return usersService.createUser(fullName, userName, password, email, avatar);
	}
	
	@GetMapping("/{id}")
	public Users getCustomerById(@PathVariable Integer id) {
		return usersService.getCustomerById(id);
	}

}
