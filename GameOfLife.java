/*According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British 
mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors 
(horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use 
their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active 
area encroaches the border of the array. How would you address these problems?*/

class Solution {
    
    //solution1: O(1) space
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {  
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
                //因为In the beginning, every 2nd bit is 0, 所以所有细胞最开始都是死亡状态, 那么如果一个细胞符合条件要在2nd bit变为
                //死亡状态, 那么就其实就不需要更改它的状态了, 因为默认就是死亡状态, 所以周围活细胞<2, >3这种就不用判断更改了, 因为判断
                //完也是要把2nd bit改为死亡状态, 而2nd bit默认已经是死亡状态了, 所以不用判断<2, >3了
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }
    
    private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
    /*To solve it in place, we use 2 bits to store 2 states:

    [2nd bit, 1st bit] = [next state, current state]

    - 00  dead (next) <- dead (current)
    - 01  dead (next) <- live (current)  
    - 10  live (next) <- dead (current)  
    - 11  live (next) <- live (current) 
    In the beginning, every cell is either 00 or 01.
    Notice that 1st state is independent of 2nd state.
    Imagine all cells are instantly changing from the 1st to the 2nd state, at the same time.
    Let's count # of neighbors from 1st state and set 2nd state bit.
    Since every 2nd state is by default dead, no need to consider transition 01 -> 00.
    In the end, delete every cell's 1st state by doing >> 1.
    For each cell's 1st bit, check the 8 pixels around itself, and set the cell's 2nd bit.

    Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
    Transition 00 -> 10: when board == 0 and lives == 3.
    To get the current state, simply do

    board[i][j] & 1
    To get the next state, simply do

    board[i][j] >> 1*/
    
    
    //solution2: 针对输入是boolean[][] board的情况
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int rows = board.length, cols = board[0].length;
        // previous row always exists (it's all 0s above board's first row)
        int[] prevRow = new int[cols];
        for (int r = 0; r < rows; ++r) {
            int prev = 0; // fakes a dead cell in front of current row
            for (int c = 0; c < cols; ++c) {
                int neigh = neigh(board, r, c, prevRow, prev);
                if (0 <= c-1) prevRow[c-1] = prev; // only overwrite once not needed
                prev = board[r][c];
                board[r][c] =        neigh == 3 /* lives on(3)/reproduction */
                    || board[r][c] + neigh == 3 /* lives on(2) */ ? 1 : 0;
            }
            prevRow[cols-1] = prev; // fill in the gap for last item's late write
        }
    }
    
    /**
     *     prevRow[c-1]*     prevRow[c]      prevRow[c+1]*
     *             prev  board[r  ] [c]  board[r  ] [c+1]*
     * board[r+1]*[c-1]* board[r+1]*[c]* board[r+1]*[c+1]*
     *
     * starred: needs bound check
     */
    private int neigh(int[][] board, int r, int c, int[] prevRow, int prev) {
        int rows = board.length, cols = board[0].length;
        int neigh = prev; // remember, board[r][c] doesn't count;
        if (true)          neigh += validSum(prevRow, c); // prevRow always exists
        if (c+1 <= cols-1) neigh += board[r][c+1]; // only one needs validation from current row
        if (r+1 <= rows-1) neigh += validSum(board[r+1], c); // only if there's a next row
        return neigh;
    }
    private int validSum(int[] row, int c) {
        int result = 0;
        if (0 <= c-1)             result += row[c-1];
        if (true)                 result += row[c];
        if (c+1 <= row.length-1)  result += row[c+1];
        return result;
    }
}
