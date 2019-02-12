/*Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally 
(horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.*/


class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int result = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    int count = maxArea(grid, 1, i, j);
                    if(count > result) {
                    	result = count;
                    }
                }
            }
        }
        return result;
    }
    
    private int maxArea(int[][] grid, int count, int i, int j) {
        grid[i][j] = 0; //一定要清0, 要不就死递归了; 清0不用恢复为1的原因是每次计算面积的时候相邻的1一定都计算完并改成0了, 再恢复为1就是
        //重复计算
        if(i > 0 && grid[i - 1][j] == 1) { //向4个方向进行递归, 注意不能有else
            count = maxArea(grid, count + 1, i - 1, j); //把count的值每次都保存下来, 作为当前已经搜索到的岛屿面积
        } 
        if(i < grid.length - 1 && grid[i + 1][j] == 1) {
            count = maxArea(grid, count + 1, i + 1, j);
        } 
        if(j > 0 && grid[i][j - 1] == 1) {
            count = maxArea(grid, count + 1, i, j - 1);
        } 
        if(j < grid[0].length - 1 && grid[i][j + 1] == 1) {
            count = maxArea(grid, count + 1, i, j + 1);
        }
        return count;
    }
}
