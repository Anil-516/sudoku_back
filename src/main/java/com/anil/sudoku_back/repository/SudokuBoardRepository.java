package com.anil.sudoku_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.sudoku_back.model.SudokuBoard;

public interface SudokuBoardRepository extends JpaRepository<SudokuBoard, Long> {
}

