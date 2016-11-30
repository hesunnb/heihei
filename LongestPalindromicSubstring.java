/*Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"*/

public class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        char[] chars = s.toCharArray();
        int length = s.length();
        while(length > 0) {
            for(int i = 0; i + length - 1 < chars.length; i++) {
                int left = i;
                int right = i + length - 1;
                boolean find = true;
                while(left < right) {
                    if(chars[left] != chars[right]) { //每一段都要这样试一遍
                        find = false;
                        break;
                    }
                    left++; //别忘了++和--
                    right--;
                }
                if(find) {
                    return s.substring(i, i + length); //注意是length
                }
            }
            length--; //先从整个字符串的长度找, 没有长度减一再找所有的可能, 一直找到最后长度单位为1
        }
        return "";
    }
}
