/*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or 
vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.*/

public class Solution {
  
    //循环写递归法
    int[][] dirs = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(helper(board, word, i, j, 0)) {
                        return true;
                    } 
                }
            }
        }
        
        return false;
    }
    
    public boolean helper(char[][] board, String word, int x, int y, int pos) {
        if(pos == word.length()) {
            return true;
        }
        
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == ' ' || board[x][y] != word.charAt(pos)) {
            return false;
        }
        
        char tmp = board[x][y];
        board[x][y] = ' ';
        for(int i = 0; i < dirs.length; i++) {
            int nextX = x + dirs[i][0];
            int nextY = y + dirs[i][1];
            if(helper(board, word, nextX, nextY, pos + 1)) {
                board[x][y] = tmp;
                return true;
            }
        }
        board[x][y] = tmp;
        return false;
    }
  
  
    public boolean exist(char[][] board, String word) {
        // write your code here
        
        if(board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) { //之后第一个字母相等的时候再进行递归, 能稍微减点复杂度
                    if(findWord(board, i, j, word, 0)) {
                        return true; //如果递归返回的是真, 那么直接就返回真, 如果是假就接着找
                    }
                }
            }
        }
        return false;
    }
    
    private boolean findWord(char[][] board, int i, int j, String word, int count) {
    	
        if(board[i][j] == word.charAt(count)) {
            board[i][j] = ' '; //如果相等就先把board这个值改为' '
            count++; //count++变成下一个字母的下标
            if(count == word.length()) { //这个判断要紧接着count++, 否则容易出错, 不好改
                //board[i][j] = word.charAt(count - 1);
                return true;
            }
            if(i > 0 && board[i - 1][j] != ' ') { //向4个方向进行递归, 注意不能有else
                if(findWord(board, i - 1, j, word, count)) {
                    //board[i][j] = word.charAt(count - 1);
                    return true; //递归回来如果为真就不再向下进行, 直接返回真了
                }
            } 
            
            if(i < board.length - 1 && board[i + 1][j] != ' ') {
                if(findWord(board, i + 1, j, word, count)) {
                    //board[i][j] = word.charAt(count - 1);
                    return true;
                }
            } 
            
            if(j > 0 && board[i][j - 1] != ' ') {
                if(findWord(board, i, j - 1, word, count)) {
                    //board[i][j] = word.charAt(count - 1);
                    return true;
                }
            } 
            
            if(j < board[0].length - 1 && board[i][j + 1] != ' ') {
                if(findWord(board, i, j + 1, word, count)) {
                    //board[i][j] = word.charAt(count - 1);
                    return true;
                }
            }
            board[i][j] = word.charAt(count - 1); //每次回溯的时候把原来的值填好, 准备进行其它方向的递归, 
            //因为回溯就肯定是没有找到合适的(找到合适的直接就一路true返回了, 原来的值不填好就不填好了, true的时候一路返回并没有把board
            //填回原样, 如果想填回原样, 就把上面if中注释的board[i][j] = word.charAt(count - 1);打开就行), 
            //所以回溯的时候要把原来改变的' '改回为原来的字母
        }
        
        return false;
    }
}
