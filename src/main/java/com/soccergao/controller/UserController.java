package com.soccergao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccergao.entity.mongo.User;
import com.soccergao.repository.mongo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
