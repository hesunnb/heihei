/*Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a 
subsequence and not a substring.*/


class Solution {
    //test case就想: abcabcbb, pwwkew就行了
    //windowSum + two pointer的思想
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>();
        int begin = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        while(end < s.length()) { 
            if(!set.contains(s.charAt(end))) { //不重复就加入
                set.add(s.charAt(end));
                end++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(begin)); //重复的话把前面的删掉, 然后把后面的加入
                begin++;
            }
        }
        return max;
    }
}
