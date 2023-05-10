package com.book.projectps.service.impl;


import com.book.projectps.exceptions.UserAlreadyExistsException;
import com.book.projectps.exceptions.UserNotFoundException;
import com.book.projectps.model.User;
import com.book.projectps.repository.UserRepository;
import com.book.projectps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
            userRepository.deleteById(id);
    }

    @Override
    public void login(User user) {


        if (user == null) {
            throw new UserNotFoundException("User not found with this email or username");
        }

        // Update the "logged" attribute of the existing user
        user.setLogged(true);
        userRepository.save(user);

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password);
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