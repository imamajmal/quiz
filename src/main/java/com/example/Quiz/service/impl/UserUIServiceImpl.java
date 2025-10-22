package com.example.Quiz.service.impl;

import com.example.Quiz.entity.User;
import com.example.Quiz.repository.UserRepository;
import com.example.Quiz.service.UserUIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUIServiceImpl implements UserUIService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));
        if (!passwordEncoder.matches(password, u.getPassword()))
            throw new RuntimeException("Invalid password");
        return u;
    }
}

