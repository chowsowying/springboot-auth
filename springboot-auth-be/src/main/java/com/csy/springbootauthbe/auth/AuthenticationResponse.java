package com.csy.springbootauthbe.auth;

import com.csy.springbootauthbe.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String message;
    private UserResponse user; // UserDto class to represent user details
}

