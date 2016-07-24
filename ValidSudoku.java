/*Determine whether a Sudoku is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. */

class Solution {
    /**
      * @param board: the board
        @return: wether the Sudoku is valid
      */
    public boolean isValidSudoku(char[][] board) {
        
        if(board == null) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        //check row
        for(int i = 0; i < m; i++) {
            boolean[] result = new boolean[n];
            for(int j = 0; j < n; j++) {
                if(board[i][j] != '.') {
                    if(result[board[i][j] - '0' - 1] == false) {
                        result[board[i][j]  - '0' - 1] = true;
                    } else {
                        return false;
                    }
                } else {
                    continue;
                }
            }
        }
        
        //check col
        for(int i = 0; i < n; i++) {
            boolean[] result = new boolean[n];
            for(int j = 0; j < m; j++) {
                if(board[j][i] != '.') {
                    if(result[board[j][i]  - '0'- 1] == false) {
                    result[board[j][i]  - '0' - 1] = true;
                    } else {
                        return false;
                    }
                } else {
                    continue;
                }
            }
        }
        
        //check submatrix
        for(int i = 0; i<9; i+= 3){
            for(int j = 0; j<9; j+= 3){
                boolean[] result = new boolean[n];
                for(int k = 0; k < 9; k++){
                    if(board[i + k/3][ j + k%3] != '.') {
                        if(result[board[i + k/3][ j + k%3] - '0'  - 1] == false) {
                        result[board[i + k/3][ j + k%3] - '0'  - 1] = true;
                        } else {
                            return false;
                        }
                    } else {
                        continue;
                    }                 
                }
            }
        }
        
        return true;
    }
};
