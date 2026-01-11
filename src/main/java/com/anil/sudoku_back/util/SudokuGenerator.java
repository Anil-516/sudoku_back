package com.anil.sudoku_back.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    
     public static int[][] generateBoard(String difficulty) {
        int[][] board = new int[9][9];
        fillBoard(board);
        int removals = switch (difficulty.toLowerCase()) {
            case "easy" -> 35;
            case "medium" -> 45;
            case "hard" -> 55;
            default -> 35;
        };
        int[][] puzzle = deepCopy(board);
        removeNumbers(puzzle, removals);
        return puzzle;
    }
    private static boolean fillBoard(int[][] board) {
        List<Integer> nums = new ArrayList<>();
        for (int i=1; i<=9; i++) nums.add(i);
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                if (board[row][col] == 0) {
                    Collections.shuffle(nums);
                    for (int num: nums) {
                        if (isValid(board, row, col, num)) {
                            board[row][col]=num;
                            if (fillBoard(board)) return true;
                            board[row][col]=0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean isValid(int[][] board, int row, int col, int val) {
        for (int i=0; i<9; i++) if (board[row][i]==val || board[i][col]==val) return false;
        int boxY=row-row%3, boxX=col-col%3;
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) if (board[boxY+i][boxX+j]==val) return false;
        return true;
    }
    public static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[9][9];
        for(int i=0;i<9;i++) copy[i]=board[i].clone();
        return copy;
    }
    private static void removeNumbers(int[][] board, int removals) {
        Random rnd = new Random();
        for(int k=0;k<removals;) {
            int i = rnd.nextInt(9), j = rnd.nextInt(9);
            if (board[i][j]!=0) {
                int temp=board[i][j]; board[i][j]=0;
                if (hasUniqueSolution(deepCopy(board))) k++;
                else board[i][j]=temp;
            }
        }
    }
    public static boolean hasUniqueSolution(int[][] board) {
        return solveAndCount(board, 0, 0, new int[]{0})==1;
    }
    private static int solveAndCount(int[][] board, int row, int col, int[] count) {
        if (row==9) return ++count[0];
        if (board[row][col]!=0) {
            if (col==8) return solveAndCount(board, row+1,0,count);
            return solveAndCount(board, row,col+1,count);
        } else {
            for (int num=1; num<=9; num++) {
                if (isValid(board,row,col,num)) {
                    board[row][col]=num;
                    if (col==8) solveAndCount(board,row+1,0,count);
                    else solveAndCount(board,row,col+1,count);
                    board[row][col]=0;
                }
            }
        }
        return count[0];
    }
    public static boolean solve(int[][] board) {
        for(int row=0;row<9;row++) for(int col=0;col<9;col++) if(board[row][col]==0) {
            for(int k=1;k<=9;k++) if(isValid(board,row,col,k)) {
                board[row][col]=k;
                if(solve(board)) return true;
                board[row][col]=0;
            }
            return false;
        }
        return true;
    }
}
