/*
Given a string source and a string target, find the minimum window in source which will contain all the characters in target.
Notice

If there is no such window in source that covers all characters in target, return the emtpy string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.

 Clarification

Should the characters in minimum window has the same order in target?

    Not necessary.

Example

For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"
*/

public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
     
    //复杂度: O(n^2)
    public String minWindow(String source, String target) {
        // write your code
        if(source == null || target == null || source.length() == 0) {
            return "";
        }
        
        String minStr = "";
        int len = Integer.MAX_VALUE;
        int sourcehash[] = new int[256];
        int targethash[] = new int[256];
        
        initTargethash(target, targethash);
        int i = 0; 
        int j = 0;
        for(i = 0; i < source.length(); i++) { //i与j之间卡着有效的字符串, 就是包含着target字符串
            while(!valid(sourcehash, targethash) && j < source.length()) { //如果处于无效状态, 就是i与j之间没有夹着有效字符串, 那么就要接着从j处开始向后添加新的字符, 一直到有效
                sourcehash[source.charAt(j++)]++;
            }
            
            if(valid(sourcehash, targethash)) { //如果有效, 就是target在i与j所卡的范围内
                if(len > j - i) {
                    len = j - i; //让长度变小
                    minStr = source.substring(i, j); //取子串
                }
            }
            sourcehash[source.charAt(i)]--; //因为i处有效已经判断过了, 那么就让i处的字母不再参与判断
        }
        return minStr;
    }
    
    private void initTargethash(String target, int[] targethash) {
        for(int i = 0; i < target.length(); i++) {
            targethash[target.charAt(i)]++;
        }
    }
    
    private boolean valid(int[] sourcehash, int[] targethash) {
        for(int i = 0; i < sourcehash.length; i++) {
            if(targethash[i] > sourcehash[i]) { //无效状态, 因为sourcehash没有囊括所有的target字符串
                return false;
            }
        }
        return true; //从头到位都不大于的话, 说明此时有效
    }
}
