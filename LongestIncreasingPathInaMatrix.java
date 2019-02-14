/*Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the 
boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.*/

class Solution {

    //dfs, 直接暴力搜索会超时, 所以超时的话就要记忆化搜索
    public int longestIncreasingPath(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int[][] cache = new int[grid.length][grid[0].length]; //记忆数组
        int result = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                result = Math.max(result, lipHelper(grid, i, j, cache)); //每一个cell出发的结果, 取最大
            }
        }
        return result;
    }
    
    private int lipHelper(int[][] grid, int i, int j, int[][] cache) {
        if(cache[i][j] != 0) {
            return cache[i][j];
        }
        int max = 1; //因为cache数组初始化的时候全是0, 所以对于四个方向都不能走的大值, 直接在下面给值为1; 相当于把平常传的count
        //写在了函数里面, 值得学习
        if(i > 0 && grid[i - 1][j] > grid[i][j]) { 
            max = Math.max(max, 1 + lipHelper(grid, i - 1, j, cache)); //每个方向的最大值, 然后保存到max里面
        } 
        if(i < grid.length - 1 && grid[i + 1][j] > grid[i][j]) {
            max = Math.max(max, 1 + lipHelper(grid, i + 1, j, cache));
        } 
        if(j > 0 && grid[i][j - 1] > grid[i][j]) {
            max = Math.max(max, 1 + lipHelper(grid, i, j - 1, cache));
        } 
        if(j < grid[0].length - 1 && grid[i][j + 1] > grid[i][j]) {
            max = Math.max(max, 1 + lipHelper(grid, i, j + 1, cache));
        }
        cache[i][j] = max; //最大值更新到cache中
        return max;
    }
}
