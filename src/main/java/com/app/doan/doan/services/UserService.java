package com.app.doan.doan.services;
import javax.swing.text.PasswordView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordView passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public boolean passwordsMatch(String rawPassword, String encodedPassword) {
        return PasswordView.matches(rawPassword, encodedPassword);
    }

    public void save(User user) {
        // Hash user password before saving to database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
