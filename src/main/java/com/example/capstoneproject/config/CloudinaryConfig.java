package com.example.capstoneproject.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dzxwjps3r");
        config.put("api_key", "199547234376816");
        config.put("api_secret", "UbJwltbg7VmZVOzn-9n7NLWzEyY");
        return new Cloudinary(config);
    }
}
