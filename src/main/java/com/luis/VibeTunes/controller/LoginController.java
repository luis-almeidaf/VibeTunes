package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.LoginRequestDto;
import com.luis.VibeTunes.dto.LoginResponseDto;
import com.luis.VibeTunes.repository.UserRepository;
import com.luis.VibeTunes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class LoginController {

    private JwtEncoder jwtEncoder;

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public LoginController(JwtEncoder jwtEncoder,
                           UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        var user = userRepository.findUserByUsername(loginRequest.username());
        if (user.isEmpty() || !userService.isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("usuário não cadastrado ou senha inválida");
        }

        var now = Instant.now();
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDto(jwtValue, expiresIn));
    }
}
