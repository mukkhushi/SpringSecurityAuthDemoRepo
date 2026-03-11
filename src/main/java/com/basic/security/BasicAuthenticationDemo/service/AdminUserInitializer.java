
package com.basic.security.BasicAuthenticationDemo.service;

import com.basic.security.BasicAuthenticationDemo.model.Role;
import com.basic.security.BasicAuthenticationDemo.model.User;
import com.basic.security.BasicAuthenticationDemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Default ADMIN user created");
            }

            if(userRepository.findByUsername("user").isEmpty()){
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                userRepository.save(user);
                System.out.println("Default USER user created");
            }
        };
    }
}

