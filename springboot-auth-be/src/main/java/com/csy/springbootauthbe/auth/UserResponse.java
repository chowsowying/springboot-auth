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
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private String token;
}
