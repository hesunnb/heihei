/*You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected 
horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more 
connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a 
square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:*/


class Solution {
 
    //solution1:
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid[0] == null) {
            return -1;
        }
        
        int islands = 0, neighbours = 0;

        //每个岛屿都乘以4就是每个岛的周长, neighbours * 2就是如果岛屿与岛屿相接, 那么每个岛屿都应该去掉一条边, 所以就是*2
        //每个岛屿都只计算右边和下边的, 这样除了最外边一层(比如题目中的4*4的图, 最外边包裹的那层16条边)所有的边都判断到了
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) { //如果相邻的话就会有重复边, 其实neighbours就是重复边的条数
                        neighbours++;
                    } // count down neighbours
                    
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) { //最右边一列和最下边一层是会访问到的, 最下边一层会判断右边的岛屿
                        //最右边一列会判断下边的岛屿
                        neighbours++;
                    } // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
 
 
    //solution2:(own)
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid[0] == null) {
            return -1;
        }
        
        int sum = 0;
        for(int i = 0; i < grid.length; i++) { //行
            for(int j = 0; j < grid[0].length; j++) { //列
                if(grid[i][j] == 1) {
                    if(i-1<0 || grid[i-1][j] == 0) { //上边越界或者是0
                        sum++;
                    }
                    if(i+1==grid.length || grid[i+1][j] == 0) { //下边越界或者是0
                        sum++;
                    }
                    if(j-1<0 || grid[i][j-1] == 0) { //左边越界或者是0
                        sum++;
                    }
                    if(j+1==grid[0].length || grid[i][j+1] == 0) { //右边越界或者是0
                        sum++;
                    }
                }
            }
        }
        return sum;
    }
}
