/*You are given a string SS of length NN containing only characters a and b. A substring (contiguous fragment) of SS is called a 
semi-alternating substring if it does not contain three identical consecutive characters. In other words, it does not contain either 
aaa or bbb substrings. Note that whole SS is its own substring.

Write a function, which given a string SS, returns the length of the longest semi-alternating substring of SS.

Example
Example 1

Input: "baaabbabbb"
Output: 7
Explanation: "aabbabb" is the longest semi-alternating substring.
Example 2

Input: "babba"
Output: 5
Explanation: Whole S is semi-alternating.
Example 3

Input: "abaaaa"
Output: 4
Explanation: "abaa" is the longest semi-alternating substring.
Notice
NN is an integer within the range [1,200\,000][1,200000];
string SS consists only of the characters a and/or b.*/

public class Solution {
    /**
     * @param s: the string
     * @return: length of longest semi alternating substring
     */
    
    //sliding window解法
    public int longestSemiAlternatingSubstring(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() < 3) {
            return s.length();
        }
        
        int left = 0;
        int right = 1;
        int newWord = 0;
        int count = 1;
        int result = 0;
        for(right = 1; right < s.length(); right++) {
            if(s.charAt(right) == s.charAt(right - 1)) {
                count++;
            } else { //遇到新的单词开端
                newWord = right;
                count = 1;
            }
            
            if(left < newWord && count > 2) { //把l移动到新的单词的开端, l < newWordBegin的用处在于"abaaaa"这个例子, newWordBegin一直指向aaaa
                left = newWord; //的开头不动, l会向后走, 走到第二个aaa的时候, l在newWordBegin的后面, l不会从newWordBegin从新走, 这个判断很重要
            }
            
            while(count > 2) { //向后移动l到合法的位置
                left++;
                count--;
            }
        
            result = Math.max(result, right - left + 1); //每步都计算长度
        }
        return result;
    }
}
