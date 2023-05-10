package com.book.projectps.repository;


import com.book.projectps.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByEmailOrUsername(String email, String username);
}
