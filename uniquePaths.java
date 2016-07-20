public class Solution {
    /**
     * @param n, m: positive integer (1 <= n ,m <= 100)
     * @return an integer
     */
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
        for(int i = 1; i < m; i++)
        {
            sum[i][0] = 1;
        }
        for(int i = 1; i < n; i++)
        {
            sum[0][i] = 1;
        }
        
        //填补空
        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
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
}
