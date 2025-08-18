package com.macedo.auth.authsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    @NotBlank @Email private String email;
    @NotBlank @Size(min=8,max=100) private String password;
}
