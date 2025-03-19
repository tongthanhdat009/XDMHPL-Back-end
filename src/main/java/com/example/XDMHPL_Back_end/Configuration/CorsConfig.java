package com.example.XDMHPL_Back_end.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**") // Cho phép tất cả các API
	                        .allowedOrigins("http://localhost:5173") // Chỉ cho phép từ frontend
	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức được phép
	                        .allowedHeaders("*")
	                        .allowCredentials(true); // Cho phép gửi cookie nếu cần
	            }
	        };
	    }
}
