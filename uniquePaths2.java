/*A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid 
(marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right*/

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        //在这个follow up里面给矩阵的原因是原矩阵有obstacle, 这个obstacle得规定出来, 所以给了矩阵
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
                break; //在初始化第一行和第一列的时候，只要有一个是障碍，那么障碍后面的地方就都到达不了了，所以这里就break，让sum这个点的后方全部
                //都为0, 就是没有路径到达
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
    
    
    // Discussion, Space Optimization
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int sum[] = new int[n];
        sum[0] = 1; //为了进行做和的运算, 要将第一个值设为1
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) { //对于每一行每次都要从头扫到尾, 因为有障碍的关系
                if(obstacleGrid[i][j] == 1) {
                    sum[j] = 0;
                } else if(j > 0) { //j > 0才进行递推, j = 0的时候是直接指定值的(最开始默认为1(sum[0] = 1)), 有障碍的时候强制归0, 
                    //以后j = 0的时候就全是0了)
                    sum[j] += sum[j - 1];
                }
            }
        }
        return sum[n - 1];
    }
  
  
    //O(1)空间, The idea is simple, set all obstacles to be 0 while doing the DP. No extra space is used.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        obstacleGrid[0][0]^=1; //取反
        //初始化

        for(int i = 1;i < m; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0]==1) ? 0 : obstacleGrid[i-1][0]; //这个地方不能直接是1的原因比如输入是[1,0], 输出结果是0
            //所以要追随前面的结果
        }

        for(int j = 1; j < n; j++) { //新遇到的1都变成0, 其余的都跟随上一位的结果
            obstacleGrid[0][j] = (obstacleGrid[0][j]==1)? 0 : obstacleGrid[0][j-1];
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1 ; j < n; j++) { //遇到1变成0, 其余的计算
                obstacleGrid[i][j] = (obstacleGrid[i][j]==1) ? 0 : obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}
