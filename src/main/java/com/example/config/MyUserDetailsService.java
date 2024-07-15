package com.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository usersRepository;

    // @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByName(username);

        return user.map(MyUserDetails::new).orElseThrow(()-> new UsernameNotFoundException(username + " не найден"));
    }

    public boolean saveUser(User user) {
        Optional<User> userFromDB = usersRepository.findByName(user.getName());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }

    

}
