package com.anil.sudoku_back.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.sudoku_back.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

