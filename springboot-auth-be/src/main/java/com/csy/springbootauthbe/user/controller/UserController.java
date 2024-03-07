package com.csy.springbootauthbe.user.controller;



import com.csy.springbootauthbe.auth.utils.RegisterRequest;
import com.csy.springbootauthbe.auth.utils.UserResponse;
import com.csy.springbootauthbe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/currentAdmin")
    public ResponseEntity<UserResponse> getCurrentAdmin() {
        return ResponseEntity.ok(service.getCurrentAdmin());
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(service.getCurrentUser());
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello from protected endpoint");
    }

}
