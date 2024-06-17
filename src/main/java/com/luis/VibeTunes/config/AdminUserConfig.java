package com.luis.VibeTunes.config;

import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.model.UserRole;
import com.luis.VibeTunes.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findByUsername("admin");

        if(userAdmin != null) {
            System.out.println("Admin já existe");
        } else {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }
}