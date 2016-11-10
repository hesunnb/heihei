/*Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
*/

public class Solution {
    
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }
        
        int[] freq = new int[26]; //用freq存储字符出现的次数
        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        for(int i = 0; i < s.length(); i++) {
            if(freq[s.charAt(i) - 'a'] == 1) { //只出现一次的就返回下标了
                return i;
            }
        }
        return -1;
    }
    
    public int firstUniqChar(String s) {
        if(s == null || s.length() == 0) {
            return -1;
        }
        
        Map<Character, Boolean> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i))) { //用哈希表实现, 只出现一次的就是false
                map.put(s.charAt(i), false);
            } else {
                map.put(s.charAt(i), true); //出现两次的就是true了
            }
        }
        
        for(int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == false) { //第一次出现false的就返回下标
                return i;
            }
        }
        return -1;
    }
}
