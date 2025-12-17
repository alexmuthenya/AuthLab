package com.alex.AuthLab.service;


import com.alex.AuthLab.model.User;
import com.alex.AuthLab.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public ResponseEntity<String> registerUser(String email, String password) {
        if(userRepository.findByEmail(email).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User Already Exists");
        }

        String hashedPassword = bcryptPasswordEncoder.encode(password);
        User newUser = new User(null, email, hashedPassword);
        userRepository.save(newUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Created Successfully");

    }


}
