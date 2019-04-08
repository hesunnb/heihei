/*You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. 
The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning 
one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning 
and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to 
open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:
Input: deadends = ["0000"], target = "8888"
Output: -1
Note:
The length of deadends will be in the range [1, 500].
target will not be in the list deadends.
Every string in deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.*/

class Solution {
    
    //最简单的普通bfs
    public int openLock(String[] deadends, String target) {
        if(deadends == null || deadends.length == 0 || target == null || target.length() == 0) {
            return -1;
        }
        
        Queue<String> queue= new LinkedList<>();
        Set<String> deads = new HashSet<>(new ArrayList(Arrays.asList(deadends)));
        Set<String> visited = new HashSet<>();
        
        queue.offer("0000");
        visited.add("0000");
        int level = 0;
        while(!queue.isEmpty()) { //从"0000"出发, 然后向两边走
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String str = queue.poll();
                if(deads.contains(str)) {
            	    continue;
                }
                if(target.equals(str)) {
                    return level;
                }
                for(int j = 0; j < 4; j++) { //循环会让每位都产生变化, 这些位的变化同属一层
            	    String strDown = str.substring(0, j) + (str.charAt(j) == '9' ? 0 : str.charAt(j) - '0' + 1) + str.substring(j + 1);
                    //正序产生的结果
            	    String strUp = str.substring(0, j) + (str.charAt(j) == '0' ? 9 : str.charAt(j) - '0' - 1) + str.substring(j + 1);
                    //倒序产生的结果
            	    if(!deads.contains(strDown) && !visited.contains(strDown)) {
            	        queue.offer(strDown);
            	        visited.add(strDown);
            	    }
            	    if(!deads.contains(strUp) && !visited.contains(strUp)) {
            		    queue.offer(strUp);
            		    visited.add(strUp);
            	    }
                }	
        	}
            level++;
        }
        return -1;
    }
}
