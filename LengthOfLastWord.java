/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.
Notice

A word is defined as a character sequence consists of non-space characters only.

Example

Given s = "Hello World", return 5.
*/

public class Solution {
    /**
     * @param s A string
     * @return the length of last word
     */
    
    //只在字符串本身操作, 没有开空间
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                res++;
            } else {
                return res;
            }
        }
        return res;
    }
    
    
    //开了一个数组
    public int lengthOfLastWord(String s) {
        // Write your code here
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        String[] result = s.trim().split(" ");
        String back = null;
        for(int i = result.length - 1; i >= 0; i--) {
            if(result[i] != "") {
                back = result[i];
                break;
            }
        }
        return back.length();
    }
    
    
    //直接将s拆成字符数组
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        char[] result=s.toCharArray();
        int res=0;
        for(int i=result.length-1;i>=0;i--)
        {
            if(result[i]!=' ')
                res++;
            else{
                if(res==0) //没有遇到字母就接着向前找字母
                    continue;
                else
                    break;
            }
        }
        return res;
    }
}
