package com.macedo.auth.authsystem.service;

import com.macedo.auth.authsystem.config.JwtProperties;
import com.macedo.auth.authsystem.entity.RefreshToken;
import com.macedo.auth.authsystem.entity.User;
import com.macedo.auth.authsystem.exception.TokenRefreshException;
import com.macedo.auth.authsystem.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final JwtProperties props;

    public RefreshTokenService(RefreshTokenRepository repo, JwtProperties props) {
        this.repo = repo;
        this.props = props;
    }

    @Transactional
    public String issue(User user) {
        repo.deleteByUser(user);
        var token = UUID.randomUUID().toString();
        var rt = RefreshToken.builder()
                .user(user)
                .token(token)
                .expiryDate(Instant.now().plusMillis(props.getRefreshTokenExpirationMs()))
                .revoked(false)
                .build();
        repo.save(rt);
        return token;
    }

    @Transactional
    public User validateAndGetUser(String refreshToken) {
        var rt = repo.findByToken(refreshToken)
                .orElseThrow(() -> new TokenRefreshException("Invalid refresh token"));
        if (rt.isRevoked() || rt.getExpiryDate().isBefore(Instant.now())) {
            throw new TokenRefreshException("Refresh token expired or revoked");
        }
        return rt.getUser();
    }

    @Transactional
    public void revokeAll(User user) {
        repo.deleteByUser(user);
    }
}
