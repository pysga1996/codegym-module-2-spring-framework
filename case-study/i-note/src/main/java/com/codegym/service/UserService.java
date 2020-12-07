package com.codegym.service;

import com.codegym.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);
    void save(User user);
    void delete(Long id);
}
