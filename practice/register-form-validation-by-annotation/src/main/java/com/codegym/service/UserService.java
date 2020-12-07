package com.codegym.service;

import com.codegym.model.User;

public interface UserService {

    Iterable<User> findAll();

    User findById(Long id);

    void save(User category);

    void remove(Long id);
}
