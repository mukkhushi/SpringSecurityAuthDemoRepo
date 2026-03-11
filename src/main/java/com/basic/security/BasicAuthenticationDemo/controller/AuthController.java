package com.basic.security.BasicAuthenticationDemo.controller;

import com.basic.security.BasicAuthenticationDemo.model.AuthRequest;
import com.basic.security.BasicAuthenticationDemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/test")
    String getTestHealth(){
        return "Auth Controller";
    }

    @PostMapping("/authenticate")
    String generateToken(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            return jwtUtil.generateToken(authRequest.getUsername());
        }
        catch (Exception e){
            throw e;
        }
    }
}
