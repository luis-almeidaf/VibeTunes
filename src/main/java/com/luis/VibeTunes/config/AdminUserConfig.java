package com.luis.VibeTunes.config;

import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.model.UserRole;
import com.luis.VibeTunes.repository.UserRepository;
import com.luis.VibeTunes.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {


    private UserRoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(UserRepository userRepository,
                           UserRoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(UserRole.values.ADMIN.name());
        var userAdmin = userRepository.findUserByUsername("admin");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("Admin already exists");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(roleAdmin!= null? Set.of(roleAdmin): Set.of());
                    userRepository.save(user);
                }
        );
    }
}
