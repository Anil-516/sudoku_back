package com.anil.sudoku_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.sudoku_back.model.GameSession;
import com.anil.sudoku_back.model.User;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    List<GameSession> findByUser(User user);
}
