package com.basic.security.BasicAuthenticationDemo.config;

import com.basic.security.BasicAuthenticationDemo.filters.JwtAuthFilter;
import com.basic.security.BasicAuthenticationDemo.model.Permissions;
import com.basic.security.BasicAuthenticationDemo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(auth-> auth
                                .requestMatchers(HttpMethod.GET,"/create").hasAllAuthorities(Permissions.EMPLOYEE_READ.name(),Permissions.EMPLOYEE_WRITE.name(),Permissions.EMPLOYEE_DELETE.name())
                                .requestMatchers(HttpMethod.POST,"/findALl").hasAllAuthorities(Permissions.EMPLOYEE_READ.name())

                                .requestMatchers("/authenticate").permitAll()
                                .anyRequest().authenticated()
                        );
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        //http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(CustomUserDetailsService customUserDetailsService){
       DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(customUserDetailsService);
       daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
       return new ProviderManager(daoAuthenticationProvider);
    }
}
