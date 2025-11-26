package com.alex.AuthLab.service;


import com.alex.AuthLab.model.User;
import com.alex.AuthLab.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder bcryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already in use, please choose another one");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already in use!");

        }
        String hashedPassword = bcryptPasswordEncoder.encode(password);
        User user = new User(null, email, hashedPassword, username);
        return userRepository.save(user);

    }
}
