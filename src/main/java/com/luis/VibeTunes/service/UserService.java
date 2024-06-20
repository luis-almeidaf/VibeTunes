package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateUserDto;
import com.luis.VibeTunes.dto.FindUserDto;
import com.luis.VibeTunes.dto.UpdateUserDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.model.UserRole;
import com.luis.VibeTunes.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
      return this.userRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    } //criar exceção personalizado depois

    public FindUserDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null){
            return new FindUserDto(user.getUsername());
        } else {
           return null;
        }
    }

    @Transactional
    public void newUser(CreateUserDto dto) {
        User userFromDB = userRepository.findByUsername(dto.username());
        if (userFromDB != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
        }
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(UserRole.BASIC);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("User not found with id " + id); // criar exceção depois
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser (Long id, UpdateUserDto updatedUser) throws Exception {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.username());
                    user.setPassword((updatedUser.password()));
                    user.setEmail(updatedUser.email());
                    if (updatedUser.email() != null && !updatedUser.password().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(updatedUser.password()));
                    }
                    return userRepository.save(user);
                }).orElseThrow(() -> new Exception("Nenhum usuário encontrado"));
    }

}
