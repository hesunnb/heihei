/*Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring 
together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
Example 1:
Input: "abab"

Output: True

Explanation: It's the substring "ab" twice.
Example 2:
Input: "aba"

Output: False
Example 3:
Input: "abcabcabcabc"

Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)*/


class Solution {

    //solution2:(own), testcase:"a"是false, 因为a后面没有appending的东西
    public boolean repeatedSubstringPattern(String s) {
        
        if(s == null || s.length() == 0) {
            return false;
        }
        
        for(int i = 0; i < s.length() / 2; i++) { //每次只要从头开始截取字串判断就行, 走一半就够了, 前一半与后一半比较一次就够了
            int len = i + 1;
            String baseString = s.substring(0, len); //从头开始的基础字串
            int j = 0;
            for(j = i + 1; j < s.length(); j += len) { //以len为长度向后增长比较字串与baseString
                if(j + len > s.length() || !s.substring(j, j + len).equals(baseString)) { //"aabaaba", 这种长度不满足正好分割的要加入
                //j + len > s.length()这个判断, 否则就越界了
                    break;
                }
            }
            if(j == s.length()) { //如果退出的时候j正好走到了最后, 那么就成功了
                return true;
            }
        }   
        return false;
    }
}
