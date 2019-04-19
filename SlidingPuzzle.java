/*On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the 
state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].*/

class Solution {
    
    //思路就是普通的bfs, 扫一遍board拿到起点, 终点是"123450", 然后用bfs搜索每步所有结果直到遇到终点
    public int slidingPuzzle(int[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return -1;
        }
        
        String target = "123450";
        String start = "";
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                start += board[i][j]; //得到起点
            }
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        Set<String> visited = new HashSet<>();
        
        int[][] dir = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                String cur = queue.poll();
                if(cur.equals(target)) {
                    return level;
                }
                int zeroIndex = cur.indexOf("0"); //找到0的位置
                int zeroX = (zeroIndex - zeroIndex % board[0].length) / board.length; //根据0在字符串中的位置计算出0在矩阵中的坐标
                int zeroY = zeroIndex % board[0].length;
                for(int[] d : dir) {
                    int nextX = zeroX + d[0];
                    int nextY = zeroY + d[1];
                    if(nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length) { //bfs检测坐标是否合法
                        continue;
                    }
                    int moveIndex = nextX * board[0].length + nextY; //根据合法坐标算出在字符串中的位置
                    String nextMove = getNextMoveString(cur, zeroIndex, moveIndex); //得到下一步走的字符串
                    if(visited.contains(nextMove)) { //走过了的字符串就不走了
                        continue;
                    }
                    visited.add(nextMove);
                    queue.offer(nextMove);
                }
            }
            level++;
        }
        return -1;
    }
    
    public String getNextMoveString(String cur, int zeroIndex, int moveIndex) {
        StringBuilder sb = new StringBuilder(cur);
        sb.setCharAt(zeroIndex, cur.charAt(moveIndex));
        sb.setCharAt(moveIndex, cur.charAt(zeroIndex));
        return sb.toString();
    }
}
