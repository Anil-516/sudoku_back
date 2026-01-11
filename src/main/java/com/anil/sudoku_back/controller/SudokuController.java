package com.anil.sudoku_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.sudoku_back.model.SudokuBoard;
import com.anil.sudoku_back.service.SudokuService;

@RestController
@RequestMapping("/api/sudoku")
@CrossOrigin(origins = "http://localhost:5173") 
public class SudokuController {
    @Autowired SudokuService service;

    @GetMapping("/generate")
    public SudokuBoard generate(@RequestParam String difficulty) {
        return service.createPuzzle(difficulty);
    }
    @PostMapping("/solve")
    public SudokuBoard solve(@RequestBody SudokuBoard puzzle) {
        return service.solvePuzzle(puzzle.getPuzzle());
    }
    @PostMapping("/validate")
    public boolean validate(@RequestParam String puzzle, @RequestParam int row, @RequestParam int col, @RequestParam int num) {
        return service.isValidMove(puzzle, row, col, num);
    }
    @PostMapping("/hint")
    public Integer hint(@RequestParam String puzzle, @RequestParam String solution, @RequestParam int row, @RequestParam int col) {
        return service.hint(puzzle, solution, row, col);
    }
}
