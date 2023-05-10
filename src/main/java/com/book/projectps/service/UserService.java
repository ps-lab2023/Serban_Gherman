package com.book.projectps.service;

import com.book.projectps.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void deleteById(Long id);
    User register(User user);
    public void login(User user);
    User findByUsernameAndPassword(String username, String password);
    User findByEmailOrUsername(String email, String username);
    User isLogged(String username);

    User save1(User user);
}