package com.macedo.auth.authsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/me")
    public Map<String, Object> me(Authentication auth) {
        return Map.of("user", auth.getName(), "authorities", auth.getAuthorities());
    }
}
