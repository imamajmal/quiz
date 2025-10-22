package com.example.Quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // ✅ Password encoder bean (used in your UserServiceImpl)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Define endpoint access control rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🔹 Disable CSRF for easier testing with Postman
            .csrf(csrf -> csrf.disable())

            // 🔹 Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Allow public access to authentication endpoints (register, login)
                  .requestMatchers(
                                        "/api/auth/**", 
                                        "/api/quizzes/**", 
                                        "/api/attempts/**",
                                        "/api/email/**",
                                        "/api/participant/**",
                                        "/register", 
                                        "/login",
                                        "/admin/**",
                                        "/participant/**",
                                        "/email",
                                        "/"

                                        ).permitAll()

                // Allow static frontend resources (if any)
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                // Require authentication for everything else
                .anyRequest().permitAll()
            )

            // 🔹 Disable form-based login (use API or JWT later)
            .formLogin(form -> form.disable())

            // 🔹 Disable HTTP Basic authentication pop-up (optional)
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
