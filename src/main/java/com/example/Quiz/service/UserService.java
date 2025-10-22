package com.example.Quiz.service;

import com.example.Quiz.entity.User;

public interface UserService {
    User registerUser(User user);
    String loginUser(String username, String password);
}

