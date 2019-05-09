/*Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.*/

class Solution {
    
    //Try 1 through 9 for each cell. The time complexity should be 9 ^ m (m represents the number of blanks to be filled in), 
    //since each blank can have 9 choices
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        helper(board);
    }
    
    public boolean helper(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '.') {
                    for(char c = '1'; c <= '9'; c++) {
                        if(isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if(helper(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true; //这个地方要return true, 因为最后一次进来如果是解决了数独的话, 就没有'.'了, 循环走了一圈发现没有'.'就是解决了
        //这个数独, 然后返回true
    }
    
    /*这个valid函数和isValidSudoku那道题的判断不一样, isValidSudoku已经是一个完整的board了, 所以直接扫三遍就行;
    这个isValid执行的时候, board是不完整的, 判断的是这个点能不能放这个值, 所以每个点都要扫行, 列, 3*3, 复杂度就高了*/
    public boolean isValid(char[][] board, int x, int y, char c) { 
        for(int i = 0; i < 9; i++) {
            if(board[x][i] != '.' && board[x][i] == c) {
                return false;
            }
            if(board[i][y] != '.' && board[i][y] == c) {
                return false;
            }
            if(board[3 * (x / 3) + i / 3][3 * (y / 3) + i % 3] != '.' && 
               board[3 * (x / 3) + i / 3][3 * (y / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
