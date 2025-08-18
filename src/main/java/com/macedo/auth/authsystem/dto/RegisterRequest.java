package com.macedo.auth.authsystem.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {
    @NotBlank @Size(min=2,max=120) private String name;
    @NotBlank @Email private String email;
    @NotBlank @Size(min=8,max=100) private String password;
}
