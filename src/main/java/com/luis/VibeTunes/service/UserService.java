package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.LoginRequestDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findUserById(UUID id) throws Exception {
      return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    } //criar excessão personalizado depois

    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    public boolean isLoginCorrect(LoginRequestDto loginRequestDto, PasswordEncoder passwordEncoder) {
        Optional<User> userOptional = findUserByUsername(loginRequestDto.username());
        return passwordEncoder.matches(loginRequestDto.password(),userOptional.get().getPassword());
    }

}
