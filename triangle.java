public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
     
    //思路:动态规划
    //复杂度: O(n2) (n是行数), 没开空间
    public int minimumTotal(int[][] triangle) {
        // write your code here
        
        if(triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return -1;
        }
        
        int n = triangle.length;
        for(int i = n - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                triangle[i - 1][j] = Math.min(triangle[i][j], triangle[i][j + 1]) + triangle[i - 1][j]; //从底向上
                //两两与上面的做和然后替换掉上面的数
            }
        }
        return triangle[0][0];
        
        /*
        2                   11
        3 4      --->       9 10
        6 5 7               7 6 10
        4 1 8 3             4 1 8 3   取7+4, 7+1中小的替换掉7, 以此类推
        */
    }
    
    
    //开了O(n)的空间, 时间O(n2)
    public int minimumTotal(int[][] triangle) {
        // write your code here
        
        if (triangle == null || triangle.length == 0) {
            return -1;
        }
        int n = triangle.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) { //把最后一行先拷贝出来
            dp[i] = triangle[n - 1][i];
        }
        
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]; //然后挑其中小的和上一层数进行相加合并
            }
        }
        return dp[0];
    }
    
    
    //开了O(n2)的空间, 一个二维数组, 时间O(n2)
    public int minimumTotal(int[][] triangle) {
        // write your code here
        if(triangle == null || triangle.length == 0) {
            return -1;
        }
        if(triangle[0] == null || triangle[0].length == 0) {
            return -1;
        }
        
        //状态:state
        int n = triangle.length;
        int f[][] = new int[n][n];
        
        //初始化:initialize(走两边，先把f这个矩阵的左右两边算出来)
        f[0][0] = triangle[0][0];
        for(int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle[i][0];
            f[i][i] = f[i - 1][i - 1] + triangle[i][i];
        }
        
        //自上而下:top - down(就是把中间空的地方填充了,用之前的和加上triangle里的值)
        for(int i = 2; i < n; i++) {
            for(int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle[i][j];
            }
        }
        
        //答案:answer
        int result = f[n - 1][0]; //最后一行的第一个值
        for(int i = 1; i < n; i++) {
            result = Math.min(f[n - 1][i], result);
        }
        return result;
    }
    
    //Version 1: Bottom-Up 自底向上的方法
   
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0) {
            return -1;
        }
        if (triangle[0] == null || triangle[0].length == 0) {
            return -1;
        }
        
        // state: f[x][y] = minimum path value from x,y to bottom
        int n = triangle.length;
        int[][] f = new int[n][n];
        
        // initialize 
        for (int i = 0; i < n; i++) {
            f[n - 1][i] = triangle[n - 1][i];
        }
        
        // bottom up
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle[i][j];
            }
        }
        
        // answer
        return f[0][0];
    }
}


    /*//Version 2 : Memorize Search //记忆化搜索方法
    public class Solution {
        private int n;
        private int[][] minSum;
        private int[][] triangle;
    
        private int search(int x, int y) {
            if (x >= n) {
                return 0;
            }
    
            if (minSum[x][y] != Integer.MAX_VALUE) {
                return minSum[x][y];
            }
    
            minSum[x][y] = Math.min(search(x + 1, y), search(x + 1, y + 1))
                + triangle[x][y];
            return minSum[x][y];
        }
    
        public int minimumTotal(int[][] triangle) {
            if (triangle == null || triangle.length == 0) {
                return -1;
            }
            if (triangle[0] == null || triangle[0].length == 0) {
                return -1;
            }
            
            this.n = triangle.length;
            this.triangle = triangle;
            this.minSum = new int[n][n];
    
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    minSum[i][j] = Integer.MAX_VALUE;
                }
            }
    
            return search(0, 0);
        }
    }*/
}
