package com.csy.springbootauthbe.auth.service;

import com.csy.springbootauthbe.auth.utils.AuthenticationResponse;
import com.csy.springbootauthbe.auth.utils.LoginRequest;
import com.csy.springbootauthbe.auth.utils.RegisterRequest;
import com.csy.springbootauthbe.auth.utils.UserResponse;
import com.csy.springbootauthbe.config.JWTService;
import com.csy.springbootauthbe.auth.entity.Role;
import com.csy.springbootauthbe.auth.entity.User;
import com.csy.springbootauthbe.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        // Check if the email already exists in the database
        if (repository.existsByEmail(request.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        // Create a UserDto object with user details
        UserResponse userObj = UserResponse.builder()
                .id(user.getId())
                .name(user.getFirstname() + " " + user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();

        // Return the response with the message and user details
        return AuthenticationResponse.builder()
                .message("User Registered successfully.")
                .user(userObj)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        // Create a UserDto object with user details
        UserResponse userObj = UserResponse.builder()
                .id(user.getId())
                .name(user.getFirstname() + " " + user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();

        // Return the response with the message and user details
        return AuthenticationResponse.builder()
                .message("User Login successfully.")
                .user(userObj)
                .build();
    }

}
