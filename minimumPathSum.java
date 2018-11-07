/*Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers 
along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.*/

public class Solution {

    //solution1: O(n^2)空间
    public int minPathSum(int[][] grid) {
        // write your code here
        
        /*动态规划O(m*n)
        题目中说每次只能向右或向下移动一步，这也是暗示动态规划，因为动归中两个点肯定一个离终点近，一个离终点远；如果上下左右四个方向都可以的话，
        那就可以绕圈，绕圈就分不出来哪个离终点近了，那就不是动规了
        先算第一行和第一列，然后填补中间，最右下角就是最小路径和*/
        
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int sum[][] = new int[m][n];
        
        sum[0][0] = grid[0][0];
        for(int i = 1; i < m; i++) { //求第一列
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < n; i++) { //求第一行
            sum[0][i] = sum[0][i - 1] + grid[0][i];
        }
        
        for(int i = 1; i < m; i++) { //填充其余的空格
            for(int j = 1; j < n;j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[m - 1][n - 1]; //返回最右下角值
    }
  
  
    //solution2: O(n)空间优化
    public int minPathSum(int[][] grid) {
        
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n]; 
        
        for (int i = 0; i < m; i++) {
            dp[0] += grid[i][0]; //每行首先加上第一个值
            for (int j = 1; j < n; j++) { //然后从第二个值开始
                if(i == 0) {
                    dp[j] = dp[j - 1] + grid[i][j]; //第一行不用Math.min比较, 直接求
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j]; //接下来的都要用Math.min比较
                }
            }
        }
        return dp[n - 1]; //返回最后一个值
    }
  
  
    //solution3: O(1)空间优化
    public int minPathSum(int[][] grid) {
        
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i = 1; i < m; i++) { //求第一列
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for(int i = 1; i < n; i++) { //求第一行
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        
        for(int i = 1; i < m; i++) { //填充其余的空格
            for(int j = 1; j < n;j++) {
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1]; //返回最右下角值
    }
}
