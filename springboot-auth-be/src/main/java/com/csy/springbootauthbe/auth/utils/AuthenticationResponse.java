package com.csy.springbootauthbe.auth.utils;

import com.csy.springbootauthbe.auth.utils.UserResponse;
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

