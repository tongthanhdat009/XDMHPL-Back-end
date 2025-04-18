package com.example.XDMHPL_Back_end.Controller;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.XDMHPL_Back_end.DTO.LoginRequest;
import com.example.XDMHPL_Back_end.DTO.LoginResponse;
import com.example.XDMHPL_Back_end.DTO.Users;
import com.example.XDMHPL_Back_end.Services.SessionService;
import com.example.XDMHPL_Back_end.Services.UserService;
import com.example.XDMHPL_Back_end.DTO.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // Cho phép CORS từ frontend
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private SessionService sessionService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
                                   HttpServletRequest servletRequest) {
        try {
            // 1. Xác thực tài khoản
            Users user = userService.loginValidate(
                    loginRequest.getUserIdentifier(),
                    loginRequest.getPassword(),
                    loginRequest.getRole()
            );
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Tài khoản hoặc mật khẩu không đúng."));
            }

            // 2. Lấy deviceInfo (từ client hoặc User‑Agent header)
            String deviceInfo = loginRequest.getDeviceInfo() != null
                    ? loginRequest.getDeviceInfo()
                    : servletRequest.getHeader("User-Agent");

            // 3. Tạo session và lưu vào DB
            String sessionId = sessionService.createSession(user.getUserID(), deviceInfo);

            // 4. Trả về sessionId kèm thông tin cơ bản của user
            LoginResponse resp = new LoginResponse(
                    sessionId,
                    user.getUserID(),
                    user.getUserName(),
                    user.getRole()
            );
            return ResponseEntity.ok(resp);

        } catch (Exception e) {
            // 5. Log lỗi và trả về 500
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Lỗi hệ thống: " + e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("SessionID") String sessionId) {
        sessionService.logout(sessionId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/email-check/{email}")
	private Users emailCheck(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}
	
	@GetMapping("/username-check/{userName}")
	private Users usersnameCheck(@PathVariable String userName){
		return userService.getUserByUsername(userName);
	}
	
	@GetMapping("/phone-check/{phoneNumber}")
	private Users phoneCheck(@PathVariable String phoneNumber) {
	    return userService.getUserByPhoneNumber(phoneNumber);
	}
}
