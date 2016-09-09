/*Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or 
vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? 
If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.*/


public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
     
    //没有用字典树, 从1问改进, 对于有大量重复前缀的例子跑不过
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        
        ArrayList<String> result = new ArrayList<String>();
        
        if(board == null || board.length == 0 || board[0].length == 0 || words == null || words.size() == 0) {
            return result;
        }

        for(int k = 0; k < words.size(); k++) {
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(board[i][j] == words.get(k).charAt(0)) { //之后第一个字母相等的时候再进行递归, 能稍微减点复杂度
                        if(!result.contains(words.get(k))) {
                            if(findWord(board, i, j, words.get(k), 0)) {
                                result.add(words.get(k));
                                break;
                            }
                        }
                    }
                }
                if(result.contains(words.get(k))) {
                	break;
                }
            }
        }
        
        return result;
    }
    
    private boolean findWord(char[][] board, int i, int j, String word, int count) {
    	
        if(board[i][j] == word.charAt(count)) {
            board[i][j] = ' '; //如果相等就先把board这个值改为' '
            count++; //count++变成下一个字母的下标
            if(count == word.length()) { //这个判断要紧接着count++, 否则容易出错, 不好改
                board[i][j] = word.charAt(count - 1);
                return true;
            }
            if(i > 0 && board[i - 1][j] != ' ') { //向4个方向进行递归, 注意不能有else
                if(findWord(board, i - 1, j, word, count)) {
                    board[i][j] = word.charAt(count - 1);
                    return true; //递归回来如果为真就不再向下进行, 直接返回真了
                }
            } 
            
            if(i < board.length - 1 && board[i + 1][j] != ' ') {
                if(findWord(board, i + 1, j, word, count)) {
                    board[i][j] = word.charAt(count - 1);
                    return true;
                }
            } 
            
            if(j > 0 && board[i][j - 1] != ' ') {
                if(findWord(board, i, j - 1, word, count)) {
                    board[i][j] = word.charAt(count - 1);
                    return true;
                }
            } 
            
            if(j < board[0].length - 1 && board[i][j + 1] != ' ') {
                if(findWord(board, i, j + 1, word, count)) {
                    board[i][j] = word.charAt(count - 1);
                    return true;
                }
            }
            board[i][j] = word.charAt(count - 1); //每次回溯的时候把原来的值填好, 准备进行其它方向的递归, 
            //因为回溯就肯定是没有找到合适的(找到合适的直接就一路true返回了, 原来的值不填好就不填好了, true的时候一路返回并没有把board
            //填回原样, 如果想填回原样, 就在四个方向的if中全都加入board[i][j] = word.charAt(count - 1);), 
            //所以回溯的时候要把原来改变的' '改回为原来的字母
        }
        
        return false;
    }
}
