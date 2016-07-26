/*
Given a boolean 2D matrix, find the number of islands.
Notice

0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]

return 3.
*/

public class Solution {
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        // Write your code here
        
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == true) {
                    FindIsland(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private void FindIsland(boolean[][] grid, int i, int j) {
        if(grid[i][j] == true) {
            grid[i][j] = false;
            if(i > 0 && grid[i - 1][j] == true) {
                FindIsland(grid, i - 1, j);
            } if(i < grid.length - 1 && grid[i + 1][j] == true) {
                FindIsland(grid, i + 1, j);
            } if(j > 0 && grid[i][j - 1] == true) {
                FindIsland(grid, i, j - 1);
            } if(j < grid[0].length - 1 && grid[i][j + 1] == true) {
                FindIsland(grid, i, j + 1);
            } 
        }
    }
}
