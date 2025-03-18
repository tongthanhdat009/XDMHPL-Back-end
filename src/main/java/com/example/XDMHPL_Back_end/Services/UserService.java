package com.example.XDMHPL_Back_end.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.XDMHPL_Back_end.Repositories.UserRepository;
import com.example.XDMHPL_Back_end.DTO.Users;


@Service
public class UserService {
	@Autowired
    private UserRepository usersRepository;

    public List<Users> getAllCustomers() {
        return usersRepository.findAll();
    }

    public Users createUser(String fullName, String userName, String password, String email, String avatar) {
    	Users user = new Users(fullName, userName, password, email, avatar);
        return usersRepository.save(user);
    }
    
    public Users getCustomerById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }
}
