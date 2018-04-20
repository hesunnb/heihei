/*Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and 
initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.*/

class Solution {
    public String reverseWords(String s) {
        
        StringBuilder result = new StringBuilder();
        if(s == null || s.length() == 0) {
            return result.toString();
        }
        
        String[] strArray = s.trim().split(" ");
        for(String str : strArray) {
            StringBuilder sb = new StringBuilder(str);
            result.append(sb.reverse()).append(" ");
        }
        return result.substring(0, result.length() - 1).toString();
    }
}
