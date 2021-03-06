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
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        
        queue.offer("0000");
        int level = 0;
        while(!queue.isEmpty()) { //从"0000"出发, 然后向两边走
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String str = queue.poll();
                if(deads.contains(str)) {
            	    continue;
                }
                deads.add(str); //把访问过的点都加入到deads里面
                if(target.equals(str)) {
                    return level;
                }
                for(int j = 0; j < 4; j++) { //循环会让每位都产生变化, 这些位的变化同属一层
            	    String strDown = str.substring(0, j) + (str.charAt(j) == '9' ? 0 : str.charAt(j) - '0' + 1) + str.substring(j + 1);
                    //正序产生的结果
            	    String strUp = str.substring(0, j) + (str.charAt(j) == '0' ? 9 : str.charAt(j) - '0' - 1) + str.substring(j + 1);
                    //倒序产生的结果
            	    if(!deads.contains(strDown)) {
            	        queue.offer(strDown);
            	    }
            	    if(!deads.contains(strUp)) {
                        queue.offer(strUp);
                    }
                }	
            }
            level++;
        }
        return -1;
    }
    
    //双端bfs
    public int openLock(String[] deadends, String target) {
        if(deadends == null || deadends.length == 0 || target == null || target.length() == 0) {
            return -1;
        }
        
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000"); //begin加入起点
        end.add(target); //end加入终点
        int level = 0;
        while(!begin.isEmpty() && !end.isEmpty()) { //由两边向中间走
            Set<String> tmp = new HashSet<>();
            for(String str : begin) {
                if(end.contains(str)) { //end包含begin中的值就说明从两头走到中间相遇了
                    return level;
                }
                if(deads.contains(str)) {
                    continue;
                }
                deads.add(str);
                //比如0000产生了0001, 那么0001再计算产生的时候, 0001还会尝试产生0000, 这时候就不能加入0000, 这也就是向deads中加入访问过值的原因
                for(int i = 0; i < 4; i++) {
                    String strDown = str.substring(0, i) + (str.charAt(i) == '9' ? 0 : str.charAt(i) - '0' + 1) + str.substring(i + 1);
                    //产生正序的结果
            	    String strUp = str.substring(0, i) + (str.charAt(i) == '0' ? 9 : str.charAt(i) - '0' - 1) + str.substring(i + 1);
                    //产生倒序的结果
            	    if(!deads.contains(strDown)) {
            	        tmp.add(strDown); //tmp加入本层产生的所有结果
            	    }
            	    if(!deads.contains(strUp)) {
                        tmp.add(strUp);
                    }
                }
            }
            level++;
            begin = end; //begin负责走整个循环, 相当于begin先走, begin走完一层, begin换为end的值, 然后从end开始往回走一层, 以此类推
            end = tmp; //end保存每层产生的新值
        }
        return -1;
    }
    
    //优化的双端bfs
    //By always picking a smaller set, this process could reduce a little(since in this problem the scale on both sides are similar) 
    //time complexity and memory complexity. 
    public int openLock(String[] deadends, String target) {
        if(deadends == null || deadends.length == 0 || target == null || target.length() == 0) {
            return -1;
        }
        
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        begin.add("0000"); //begin加入起点
        end.add(target); //end加入终点
        int level = 0;
        Set<String> tmp;
        while(!begin.isEmpty() && !end.isEmpty()) { //由两边向中间走
            if(begin.size() > end.size()) { //因为两边向中间走, 所以每次都挑长度最短的集合走, 因为最终都是走到相遇, 所以无论谁走
                //最终走到相遇的步数都一样
                tmp = begin;
                begin = end;
                end = tmp;
            }
            tmp = new HashSet<>();
            for(String str : begin) {
                if(end.contains(str)) { //end包含begin中的值就说明从两头走到中间相遇了
                    return level;
                }
                if(deads.contains(str)) {
                    continue;
                }
                deads.add(str);
                for(int i = 0; i < 4; i++) {
                    String strDown = str.substring(0, i) + (str.charAt(i) == '9' ? 0 : str.charAt(i) - '0' + 1) + str.substring(i + 1);
            	    String strUp = str.substring(0, i) + (str.charAt(i) == '0' ? 9 : str.charAt(i) - '0' - 1) + str.substring(i + 1);
            	    if(!deads.contains(strDown)) {
            	        tmp.add(strDown);
            	    }
            	    if(!deads.contains(strUp)) {
                        tmp.add(strUp);
                    }
                }
            }
            level++;
            begin = tmp; //把tmp给begin, 在上面决定最终要循环哪个集合
        }
        return -1;
    }
}
