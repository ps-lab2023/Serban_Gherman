package com.book.projectps.service.impl;


import com.book.projectps.exceptions.UserAlreadyExistsException;
import com.book.projectps.exceptions.UserNotFoundException;
import com.book.projectps.model.User;
import com.book.projectps.repository.UserRepository;
import com.book.projectps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User register(User user) {
        User existingUser = userRepository.findByEmailOrUsername(user.getEmail(),user.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User already exists with this email or username");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
            userRepository.deleteById(id);
    }

    @Override
    public void login(User user) {

        User existingUser = userRepository.findByEmailOrUsername(user.getEmail(), user.getDisplayName());
        System.out.println(user.getPassword());
        System.out.println(existingUser.getPassword());
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with this email or username");
        }
        //check pass
        if ( passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new UserNotFoundException("Invalid Password");
        }
        // Update the "logged" attribute of the existing user
        existingUser.setLogged(true);
        userRepository.save(existingUser);

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByEmailOrUsername("", username);
        //check pass
        if ( user != null && passwordEncoder.matches(password, user.getPassword()))
        {
            return user;

        }
        return null;
    }

    @Override
    public User findByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username);
    }

    @Override
    public User isLogged(String username) {
        User user = userRepository.findByEmailOrUsername("", username);
        if (user.getLogged()) return user;
        return null;
    }

    @Override
    public User save1(User user) {
        return userRepository.save(user);
    }


    // Implement more methods here as needed
}