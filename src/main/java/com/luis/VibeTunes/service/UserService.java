package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateUserDto;
import com.luis.VibeTunes.dto.UserResponseDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.model.UserRole;
import com.luis.VibeTunes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
      return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    } //criar excessão personalizado depois

    public UserResponseDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null){
            return new UserResponseDto(user.getUsername());
        } else {
           return null;
        }
    }

    public void newUser(CreateUserDto dto) {

        var userFromDB = userRepository.findByUsername(dto.username());
        if (userFromDB != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(UserRole.BASIC);
        userRepository.save(user);
    }

}
