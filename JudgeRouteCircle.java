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
