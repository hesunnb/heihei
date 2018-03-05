/*Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves 
back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), 
U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false*/

class Solution {
    
    //solution1:宗旨就是回到原点的话, 上下走的应该一样, 左右应该走的一样
    public boolean judgeCircle(String moves) {
        
        if(moves == null || moves.length() == 0) {
            return false;
        }
        
        int x = 0, y = 0;
        for(int i = 0; i < moves.length(); i++) {
            if(moves.charAt(i) == 'U') {
                x++;
            } else if(moves.charAt(i) == 'D') {
                x--;
            } else if(moves.charAt(i) == 'L') {
                y++;
            } else if(moves.charAt(i) == 'R') {
                y--;
            }
        }
        if(x == 0 && y == 0) {
            return true;
        }
        return false;
    }
    
    
    //solution2:(own)
    public boolean judgeCircle(String moves) {
        
        if(moves == null || moves.length() == 0) {
            return false;
        }
        
        int[] step = {0,0};
        for(int i = 0; i < moves.length(); i++) {
            if(moves.charAt(i) == 'U') {
                step[0]--;
            } else if(moves.charAt(i) == 'D') {
                step[0]++;
            } else if(moves.charAt(i) == 'L') {
                step[1]--;
            } else if(moves.charAt(i) == 'R') {
                step[1]++;
            }
        }
        if(step[0] == 0 && step[1] == 0) {
            return true;
        }
        return false;
    }
}
