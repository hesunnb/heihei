/*
Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

 Example

For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
*/

public class Solution {
    /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        if(A == null || B == null || A.length() == 0 || B.length() == 0)
        {
            return 0;
        }
        
        //开一个二维矩阵,O(n2)
        int[][] result = new int[A.length() + 1][B.length() + 1]; 
        for(int i = 1; i <= A.length(); i++) //遍历二维数组
        {
            for(int j = 1; j <= B.length(); j++)
            {
                result[i][j] = Math.max(result[i][j - 1], result[i - 1][j]); //保存目前最大值,
                //这里不用像edit distance那道题在比较中加入result[i - 1][j - 1],
                //因为result[i][j - 1], result[i - 1][j]他俩包含了result[i - 1][j - 1]这种情况
                if(A.charAt(i - 1) == B.charAt(j - 1))
                {
                    result[i][j] = result[i - 1][j - 1] + 1; //真正改变最大值的步骤
                }
            }
        }
        return result[A.length()][B.length()];
        
        /**
         *  矩阵长度大于串长一位，第一行代表ABCD与“无”进行匹配，所以都是0；第一列代表EACB与“无”进行匹配，所以也是0；
         *  这也就是动规的初始化；最终结果是result[A.length()][B.length()]，它的左上角以及继续的左上角，
         *  是相应规模子串的最终结果值，所以真正算数的是result[i][j] = result[i - 1][j - 1] + 1;　
         *  就是在上一个的最终结果值上加１，而result[i][j] = Math.max(result[i][j - 1], result[i - 1][j]);
         *  只是用来保存到现在为止目前的最大值
         *  每次匹配的位置就是相应字符串多少个位置的字母进行匹配的结果
         *      E A C B
         *    0 0 0 0 0
         *  A 0
         *  B 0
         *  C 0 
         *  D 0
         * /
    }
}
