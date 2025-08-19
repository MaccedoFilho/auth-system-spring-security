package com.macedo.auth.authsystem.exeption;

public class TokenRefreshException extends RuntimeException {
    public TokenRefreshException(String message) { super(message); }
}
