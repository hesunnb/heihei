/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid 
(marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28*/

public class Solution {

    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 0 || n == 0) {
            return 0;
        }
        
        if(m == 1 || n == 1) {
            return 1;
        }
        
        int sum[][] = new int[m][n];
        sum[0][0] = 1;
        
        //初始化
        for(int i = 1; i < m; i++) {
            sum[i][0] = 1;
        }
        for(int i = 1; i < n; i++) {
            sum[0][i] = 1;
        }
        
        //填补空
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }
        return sum[m - 1][n - 1];
    }
   
   
    // Space Optimization
    public int uniquePaths(int m, int n) {
        
        if(m == 0 || n == 0) {
            return 0;
        }
        if(m == 1 || n == 1) {
            return 1;
        }
        
        int result[] = new int[n];
        for(int i = 0; i < n; i++) {
            result[i] = 1;
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                result[j] += result[j - 1];
            }
        }
        return result[n - 1];
    }
    
    
    //更进一步优化就是把最开始赋值的那一遍也合到两个for里面去, 这样就只剩下双重for, 没有其余的for了
    public int uniquePaths(int m, int n) {
        // write your code here
        if(m == 0 || n == 0) {
            return 0;
        }
        if(m == 1 || n == 1) {
            return 1;
        }
        
        int result[] = new int[n];
        result[0] = 1;
        
        for(int i = 0; i < m; i++) {
            for(int j = 1; j < n; j++) {
                result[j] += result[j - 1];
            }
        }
        return result[n - 1];
    }
}
