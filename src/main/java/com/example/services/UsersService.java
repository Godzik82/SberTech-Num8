package com.example.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UsersService {

    @Autowired
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void newUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info(user.getPassword());
        userRepository.saveAndFlush(user);
    }

    public Long getUserId(String name){
        Optional<User> user = userRepository.findByName(name);
        return user.get().getId();
    }

}
