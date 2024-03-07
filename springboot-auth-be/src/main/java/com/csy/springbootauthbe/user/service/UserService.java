package com.csy.springbootauthbe.user.service;

import com.csy.springbootauthbe.auth.entity.Role;
import com.csy.springbootauthbe.auth.entity.User;
import com.csy.springbootauthbe.auth.repository.UserRepository;
import com.csy.springbootauthbe.auth.utils.UserResponse;
import com.csy.springbootauthbe.config.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    public UserResponse getCurrentAdmin() {
        // Get the authenticated user's email from the JWT token
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Retrieve the user from the database
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the user is an admin
        if (user.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Only admins can access this endpoint.");
        }

        // Create and return a UserResponse object
        return createUserResponse(user);
    }

    public UserResponse getCurrentUser() {
        // Get the authenticated user's email from the JWT token
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Retrieve the user from the database
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Create and return a UserResponse object
        return createUserResponse(user);
    }

    private UserResponse createUserResponse(User user) {
        // Create a UserDto object with user details
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getFirstname() + " " + user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
