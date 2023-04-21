package com.svistun.bookshoop.controller.auth;


import com.svistun.bookshoop.config.security.JwtService;
import com.svistun.bookshoop.entity.*;
import com.svistun.bookshoop.exception.UnauthorizedException;
import com.svistun.bookshoop.repository.TokenRepository;
import com.svistun.bookshoop.repository.UserRepository;
import com.svistun.bookshoop.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh.mm a");


    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getLastname())
                .firstName(request.getFirstname())
                .registerDate(LocalDateTime.now())
                .role(Role.USER)
                .isVerified(false)
                .build();
        var settingsNotification = SettingsNotification.builder()
                .isEnableEmailMessage(true)
                .isEnablePushMessage(true)
                .user(user)
                .build();
        user.setSettingsNotification(settingsNotification);
        var jwtToken = jwtService.generateToken(user);
        repository.save(user);
        emailService.sendRegisterCode(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new UnauthorizedException("User not found. Check password or email");
        }
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        emailService.sendMessage(user, "Authentication",
                "Logged in at " + LocalDateTime.now().format(dateFormat) +
                        " at " + LocalDateTime.now().format(timeFormat));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .dateTimeReceiptToken(LocalDateTime.now())
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserID());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}

