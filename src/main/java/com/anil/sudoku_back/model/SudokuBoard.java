package com.anil.sudoku_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class SudokuBoard {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String puzzle;
    @Lob
    private String solution;
    
    public static String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row: board)
            for (int num: row) sb.append(num);
        return sb.toString();
    }
    public static int[][] stringToBoard(String s) {
        int[][] b = new int[9][9];
        int idx=0;
        for (int i=0;i<9;i++) for (int j=0;j<9;j++) b[i][j]=s.charAt(idx++)-'0';
        return b;
    }
}
