/*Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.*/

class Solution {
  
    //Note里面写了, 给定的board只包含digits 1-9 and the character '.', 面试的时候得确认一下, 如果还包含'A'之类的还得判断一下
    //Character.isDigit(board[i][j])
    public boolean isValidSudoku(char[][] board) {
        
        if(board == null) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        //check row, 按照规则检查行
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
        
        //check col, 按照规则检查列
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
        
        //check submatrix, 按照规则检查子矩阵
        for(int i = 0; i < m; i+= 3){
            for(int j = 0; j < n; j+= 3){
                boolean[] result = new boolean[n];
                for(int k = 0; k < 9; k++){
                    if(board[i + k/3][j + k%3] != '.') {
                        if(result[board[i + k/3][j + k%3] - '0'  - 1] == false) {
                        result[board[i + k/3][j + k%3] - '0'  - 1] = true;
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
}
