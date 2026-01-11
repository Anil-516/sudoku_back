package com.anil.sudoku_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anil.sudoku_back.model.SudokuBoard;
import com.anil.sudoku_back.repository.SudokuBoardRepository;
import com.anil.sudoku_back.util.SudokuGenerator;

@Service
public class SudokuService {
    @Autowired SudokuBoardRepository boardRepo;
    
    public SudokuBoard createPuzzle(String difficulty) {
        int[][] puz = SudokuGenerator.generateBoard(difficulty);
        int[][] sol = SudokuGenerator.deepCopy(puz);
        SudokuGenerator.solve(sol);
        SudokuBoard board = new SudokuBoard();
        board.setPuzzle(SudokuBoard.boardToString(puz));
        board.setSolution(SudokuBoard.boardToString(sol));
        return boardRepo.save(board);
    }
    public SudokuBoard solvePuzzle(String puzzle) {
        int[][] puz = SudokuBoard.stringToBoard(puzzle);
        if (!SudokuGenerator.solve(puz)) return null;
        SudokuBoard board = new SudokuBoard();
        board.setPuzzle(puzzle);
        board.setSolution(SudokuBoard.boardToString(puz));
        return board;
    }
    public boolean isValidMove(String puzzleString, int row, int col, int num) {
        int[][] puz = SudokuBoard.stringToBoard(puzzleString);
        return SudokuGenerator.isValid(puz, row, col, num);
    }
    public Integer hint(String puzzleString, String solutionString, int row, int col) {
        int[][] puz=SudokuBoard.stringToBoard(puzzleString), sol=SudokuBoard.stringToBoard(solutionString);
        if(puz[row][col]!=0) return null;
        return sol[row][col];
    }
}
