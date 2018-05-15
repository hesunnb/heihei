/*Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that 
is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent 
cells connected horizontally or vertically.*/

class Solution {
    
    /*First, check the four border of the matrix. If there is a element is
    'O', alter it and all its neighbor 'O' elements to '1'.

    Then ,alter all the 'O' to 'X'

    At last,alter all the '1' to 'O'

    For example:

             X X X X           X X X X             X X X X
             X X O X  ->       X X O X    ->       X X X X
             X O X X           X 1 X X             X O X X
             X O X X           X 1 X X             X O X X*/
    public void solve(char[][] board) {

	if (board == null || board.length <= 2 || board[0].length <= 2) { //小于等于2的时候都是边, 没有包裹的时候存在
		return;
	}

	int row = board.length;
	int col = board[0].length;

	for (int i = 0; i < row; i++) {
	    check(board, i, 0, row, col); //检查最左侧一列, 行在变而列不动
	    if (col > 1) {
		check(board, i, col - 1, row, col); //检查最右侧一列
	    }
	}
	for (int j = 1; j + 1 < col; j++) { //检查最顶上一行, 不包括第一个值和最后一个值, 因为检查列的时候已经检查过了
	    check(board, 0, j, row, col);
	    if (row > 1) {
		check(board, row - 1, j, row, col); //检查最底下一行
            }
	}
	for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') { //全矩阵把'O'变'X'
                    board[i][j] = 'X';
                }
            }
        }
	for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') { //'1'变'O'
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void check(char[][] board, int i, int j, int row, int col) {
	if (board[i][j] == 'O') { //因为是从四个边出发, 所以检查出'O'进行递归, 把跟这个'O'相连的所有'O'都找出来
	    board[i][j] = '1'; //把找到的相关的'O'都变成'1'
	    if (i > 1) {
		check(board, i - 1, j, row, col); //上
	    }
	    if (j > 1) {
		check(board, i, j - 1, row, col); //左
	    }
	    if (i + 1 < row) {
		check(board, i + 1, j, row, col); //下
	    }
	    if (j + 1 < col) {
		check(board, i, j + 1, row, col); //右
	    }
	}
    }
}
