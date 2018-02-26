/*Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.*/


class Solution {
    
    //solution1: 例子里面只有这三种情况的字符串; 中间带空格的, 然后其它字符像.;-这些testcase中都没有
    public boolean detectCapitalUse(String word) {
        
        if(word == null || word.length() == 0) {
            return false;
        }
        
        int cnt = 0;
        for(char c: word.toCharArray()) {
            if('Z' - c >= 0) cnt++;
        }
        return (cnt==0 || cnt==word.length() || (cnt==1 && 'Z' - word.charAt(0)>=0));
        //分别对应全是小写, 全是大写, 首字母大写
    }
    
    
    //solution2: 例子里面只有这三种情况的字符串; 中间带空格的, 然后其它字符像.;-这些testcase中都没有
    public boolean detectCapitalUse(String word) {
        
        if(word == null || word.length() == 0) {
            return false;
        }
        
        int i = 0; 
        while(i < word.length() && Character.isLowerCase(word.charAt(i))) { //全小写判断一遍
            i++;
        }
        if(i == word.length()) return true;

        i = 0;
        while(i < word.length() && Character.isUpperCase(word.charAt(i))) { //全大写判断一遍
            i++;
        }
        if(i == word.length()) return true;
        
        i = 0;
        if(Character.isUpperCase(word.charAt(i))) {
            i++;
            while(i < word.length() && Character.isLowerCase(word.charAt(i))) { //首字母大写的判断一遍
                i++;
            }
            if(i == word.length()) return true;
        }
        
        return false;
    }
}
