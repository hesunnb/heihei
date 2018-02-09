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

    //solution1:运用周期性, 如果s里面的一个子串可以重复几次就能完全表示s, 那么s就具有周期性, s+s就是仅仅使用两个s单元连接来验证周期性,
    //所以就转化为验证s是否有周期性的问题, s+s之后去掉头尾, 保证原来的两个s不具有周期性, 然后看两个s的连接处有没有周期性, 如果有, 说明
    //s本身就具有周期性, 那么具有周期性的字符串自然能够找出其中的某个子串来组成s
    public boolean repeatedSubstringPattern(String s) {
        
        if(s == null || s.length() == 0) {
            return false;
        }
        
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);  
    }
    /*The explanation for why that works is pretty straight forward.

    Consider a string S="helloworld". Now, given another string T="lloworldhe", can we figure out if T is a rotated version of S? 
    Yes, we can! We check if S is a substring of T+T.

    Fine. How do we apply that to this problem? We consider every rotation of string S such that it’s rotated by k units [k < len(S)] 
    to the left. Specifically, we’re looking at strings "elloworldh", "lloworldhe", "loworldhel", etc...

    If we have a string that is periodic (i.e. is made up of strings that are the same and repeat R times), then we can check if the 
    string is equal to some rotation of itself, and if it is, then we know that the string is periodic. Checking if S is a sub-string 
    of (S+S)[1:-1] basically checks if the string is present in a rotation of itself for all values of R such that 0 < R < len(S).*/
    
    
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
