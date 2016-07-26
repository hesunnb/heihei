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
        char[] schr=s.toCharArray();
        if(s==null || schr.length==0)
            return 0;
        int res=0;
        for(int i=schr.length-1;i>=0;i--)
        {
            if(schr[i]!=' ')
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
