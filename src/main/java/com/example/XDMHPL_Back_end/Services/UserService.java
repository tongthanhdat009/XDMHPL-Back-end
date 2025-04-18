package com.example.XDMHPL_Back_end.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.XDMHPL_Back_end.Repositories.UserRepository;
import com.example.XDMHPL_Back_end.DTO.Users;


@Service
public class UserService {
	@Autowired
    private UserRepository usersRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    
    public Users createUser(String fullName, String userName, String password, String email, String avatar, String phoneNumber, LocalDate dateOfBirth, String gender
			, String coverPhotoURL, String sessionID, String role) {
        String encryptedPassword = passwordEncoder.encode(password);
    	Users user = new Users(fullName, userName, encryptedPassword, email, avatar,phoneNumber, dateOfBirth, gender, coverPhotoURL, sessionID, null, role);
        return usersRepository.save(user);
    }
    
    public Users getUserById(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }
    
    public Users getUserByUsername(String userName){
    	return usersRepository.findByUserName(userName);
    }
    
    public Users getUserByPhoneNumber(String phoneNumber){
    	return usersRepository.findByPhoneNumber(phoneNumber);
    }
    
    public Users getUserByEmail(String email) {
    	return usersRepository.findByEmail(email);
    }
    
    public Users loginValidate(String userIdentifier, String password, String role) {
        String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        userIdentifier = userIdentifier.trim();

        Users user = userIdentifier.matches(emailRegex)
            ? usersRepository.findByEmail(userIdentifier)
            : usersRepository.findByUserName(userIdentifier);

        if (user == null) {
            return null;
        }

        if (passwordEncoder.matches(password, user.getPassword()) && user.getRole().equalsIgnoreCase(role)) {
            return user;
        }

        return null;
    }
}
