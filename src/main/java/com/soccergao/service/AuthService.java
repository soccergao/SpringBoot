package com.soccergao.service;

import com.soccergao.entity.mongo.User;

public interface AuthService {
	User register(User user);
    String login(String username, String password);
    String refresh(String oldToken);
}
