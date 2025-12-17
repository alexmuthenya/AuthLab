package com.alex.AuthLab.service;


import com.alex.AuthLab.dto.ApiResponse;
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

    public ResponseEntity<ApiResponse> registerUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body( new ApiResponse("User Already Exists", false));
        }

        String hashedPassword = bcryptPasswordEncoder.encode(user.getPassword());
        User newUser = new User(null, user.getEmail(), hashedPassword);
        userRepository.save(newUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse("User registered successfully", true));

    }


}
