/*Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4*/

class Solution {

    //solution1: 动归, O(mn)时间, O(mn)空间
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
      
        int max = 0, n = matrix.length, m = matrix[0].length;

        // dp(i, j) represents the length of the square 
        // whose lower-right corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
        int[][] dp = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
              if(matrix[i - 1][j - 1] == '1') {
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                max = Math.max(max, dp[i][j]);
              }
            }
        }

        // return the area
        return max * max;
    }
    /*
    1 0 1 0 0
    1 0 1 1 1
    1 1 1 1 1
    1 0 0 1 0
    
    0 0 0 0 0 0 //这个就是dp这个矩阵完成时的样子, 其实就是在原矩阵的外围加了一圈0, 剩下的就是按照规则把原矩阵填了进去, 可以看出这个矩阵除了0行0列
    0 1 0 1 0 0 //之外和原矩阵特别相似, 每次判断matrix[i - 1][j - 1] == '1', 其实就是判断在dp矩阵中的dp[i][j]自己, 在外面加一圈0就是为了能把
    0 1 0 1 1 1 //Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1这个方法一直套用下去, 并且原矩阵的第0行和第0列永远
    0 1 1 1 2 2 //都会在dp矩阵的1行1列保留, 所以外面一圈0保留了原矩阵的0行0列, 然后基于原矩阵, 填入新矩阵
    0 1 0 0 1 0
    */
}
