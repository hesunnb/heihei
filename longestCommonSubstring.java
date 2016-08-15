/*
Given two strings, find the longest common substring.

Return the length of it.
Notice

The characters in substring should occur continuously in original string. This is different with subsequence.

 Example

Given A = "ABCD", B = "CBCE", return 2.
*/

public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if(A == null || B == null || A.length() == 0 || B.length() == 0)
        {
            return 0;
        }
        
        //开一个二维矩阵, O(n x m) time and memory.
        int[][] result = new int[A.length() + 1][B.length() + 1];
        int max = 0;
        for(int i = 1; i <= A.length(); i++) //遍历二维数组
        {
            for(int j = 1; j <= B.length(); j++)
            {
                if(A.charAt(i - 1) == B.charAt(j - 1))
                {
                    result[i][j] = result[i - 1][j - 1] + 1; //和最长公共子序列不一样的地方就是这里不用每步都保留最大值，
                    //一样就加１，不一样就是０，然后用一个ｍａｘ得到最大的那个就行, 因为连续的时候一定是i也大了一位, j同时也
                    //大了一位, 所以才会去找左上角+1
                    max = Math.max(max, result[i][j]); 
                }
            }
        }
        return max;
    }
}
