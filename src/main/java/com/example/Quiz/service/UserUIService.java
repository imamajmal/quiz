package com.example.Quiz.service;

import com.example.Quiz.entity.User;

public interface UserUIService {
    User register(User user);
    User login(String username, String password);
}

