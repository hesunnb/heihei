/*Given k strings, find the longest common prefix (LCP).
Example
For strings "ABCD", "ABEF" and "ACEF", the LCP is "A"

For strings "ABCDEFG", "ABCEFG" and "ABCEFA", the LCP is "ABC"*/

public class Solution {
    // 1. Method 1, start from the first one, compare prefix with next string, until end;
    // 2. Method 2, start from the first char, compare it with all string, and then the second char

    // 1. Method 1, start from the first one, compare prefix with next string, until end;
    public String longestCommonPrefix(String[] strs) {
        
        if(strs == null || strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++) {
            int j = 0;
            while(j < prefix.length() && j < strs[i].length() && prefix.charAt(j) == strs[i].charAt(j)) { //j没有超过prefix和strs[i]的
            //长度, 并且j位置的值相等
                j++;
            }
            if(j == 0) { //没有公共前缀或者strs里面有""
                return "";
            }
            prefix = prefix.substring(0, j); //改变prefix
        }
        return prefix;
    }
    
    // 2. Method 2, start from the first char, compare it with all string, and then the second char
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int j = 0;
        String s = strs[0];
        
        while(j < s.length()) {
            char temp = s.charAt(j); //取第j位置的字符
            for(int i = 1; i < strs.length; i++) {
                if(j >= strs[i].length() || temp != strs[i].charAt(j)) { //如果j位置超过了某个字符串的长度或者有不相等的情况
                //直接就返回结果了, 因为已经不符合条件了
                    return sb.toString();
                }
            }
            sb.append(temp); //能从for循环中正常出来就说明当前这个字符符合条件, 就加入到StringBuilder当中
            j++; //取下一个字符
        }
        return sb.toString();
    }
}
