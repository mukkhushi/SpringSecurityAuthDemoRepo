package com.basic.security.BasicAuthenticationDemo.service;

import com.basic.security.BasicAuthenticationDemo.model.User;
import com.basic.security.BasicAuthenticationDemo.repository.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Nonnull
    @Override
    public UserDetails loadUserByUsername(@Nonnull String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username Not Found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
