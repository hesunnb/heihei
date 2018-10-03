/*Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".*/

class Solution {

    //version1: 把s调转一下, 然后求longestCommonSubsequence即可
    public int longestPalindromeSubseq(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        String A = s;
        StringBuilder sb = new StringBuilder(A);
        String B = sb.reverse().toString();
        //开一个二维矩阵,O(n2)
        int[][] result = new int[A.length() + 1][B.length() + 1]; 
        for(int i = 1; i <= A.length(); i++) { //遍历二维数组
            for(int j = 1; j <= B.length(); j++) { //两边各新来一个元素, 先分别和之前的字符串匹配, 再进行相等判断
                result[i][j] = Math.max(result[i][j - 1], result[i - 1][j]); //保存目前最大值,
                //这里不用像edit distance那道题在比较中加入result[i - 1][j - 1],
                //因为result[i][j - 1], result[i - 1][j]他俩包含了result[i - 1][j - 1]这种情况
                if(A.charAt(i - 1) == B.charAt(j - 1)) {
                    result[i][j] = result[i - 1][j - 1] + 1; //真正改变最大值的步骤
                }
            }
        }
        return result[A.length()][B.length()];
    }
    
    
    //version2:
    dp[i][j]: the longest palindromic subsequence's length of substring(i, j), here i, j represent left, right indexes in the string
    State transition:
    dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
    otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
    Initialization: dp[i][i] = 1

    public class Solution {
        public int longestPalindromeSubseq(String s) {
            int[][] dp = new int[s.length()][s.length()];

            for (int i = s.length() - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i+1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[0][s.length()-1];
        }
    }
}
