/*Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.*/

public class Solution {
    /**
     * Determine whether s3 is formed by interleaving of s1 and s2.
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        if(s1.length() + s2.length() != s3.length()) { //首先长度和要一样, ["", "", "1"]这种长度就不等
            return false;
        }
        
        boolean[][] result = new boolean[s1.length() + 1][s2.length() + 1];
        result[0][0] = true; //["", "", ""]这种例子
        
        for(int i = 1; i <= s1.length(); i++) { //首先查看s1本身能和s3前面重合多少
            if(s1.charAt(i - 1) == s3.charAt(i - 1) && result[i - 1][0]) {
                result[i][0] = true;
            }
        }
        
        for(int j = 1; j <= s2.length(); j++) { //再查看s2本身能和s3前面重合多少
            if(s2.charAt(j - 1) == s3.charAt(j - 1) && result[0][j - 1]) {
                result[0][j] = true;
            }
        }
        
        for(int i = 1; i <= s1.length(); i++) { //从第二行开始扫描
            for(int j = 1; j <= s2.length(); j++) { //每次扫描扫描列的长度
                if((s3.charAt(i + j - 1) == s1.charAt(i - 1) && result[i - 1][j]) || (s3.charAt(i + j - 1) == s2.charAt(j - 1) && result[i][j - 1])) { //1代表能走的路径, 0代表不能走的路径, 判断相等的同时上下看能不能走来确定路径
                    result[i][j] = true;
                }
            }
        }
        
        return result[s1.length()][s2.length()]; //返回结果
    }
    /*
    testCase: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" return true
    
        d b b c a
      1 0 0 0 0 0
    a 1 0 0 0 0 0
    a 1 1 1 1 1 0
    b 0 1 1 0 1 0
    c 0 0 1 1 1 1
    c 0 0 0 1 0 1
    
    s3 = "aadbbbaccc", return false.
    
        d b b c a
      1 0 0 0 0 0 
    a 1 0 0 0 0 0
    a 1 1 1 1 0 0
    b 0 1 1 1 0 0
    c 0 0 0 0 0 0
    c 0 0 0 0 0 0
    */
}
