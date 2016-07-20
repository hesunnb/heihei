public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] sum = new int[m][n];
        
        //初始化
        for(int i = 0; i < m; i++) {
            if(obstacleGrid[i][0] == 0) { //一直都是拿obstacleGrid原数组做比对, 然后再相应的向自己的sum数组中填值
                sum[i][0] = 1;
            } else {
                break; //在初始化第一行和第一列的时候，只要有一个是障碍，那么障碍后面的地方就都到达不了了，所以这里就break，让sum这个点的后方全部都为０，就是没有路径到达
            }
        }
        
        for(int i = 0; i < n; i++) {
            if(obstacleGrid[0][i] == 0) {
                sum[0][i] = 1;
            } else {
                break;
            }
        }
        
        //填空
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] == 1) { //障碍物
                    sum[i][j] = 0; //用0填充之后别的地方的值就能正常算了
                } else {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
                }
            }
        }
        return sum[m - 1][n - 1]; //如果这个矩阵的第一个点就是障碍点的话，就相当于直接返回０了
    }
}
