package com.example.qkart.repository;


import com.example.qkart.model.User;

import java.util.Optional;

public interface IUserRepository {

    void save(User user);

    Optional<User> finduserByUsername(String username);

}
