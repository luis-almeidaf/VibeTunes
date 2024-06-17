package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.config.TokenService;
import com.luis.VibeTunes.dto.CreateUserDto;
import com.luis.VibeTunes.dto.LoginRequestDto;
import com.luis.VibeTunes.dto.LoginResponseDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody CreateUserDto dto) {
        userService.newUser(dto);
        return ResponseEntity.ok(dto);
    }


}
