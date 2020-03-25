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

class Solution {
    public int numIslands(char[][] grid) {
        if(grid ==null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int result = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    findIsland(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }
    
    int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
    private void findIsland(char[][] grid, int i, int j) {
        if(grid[i][j] == '1') {
            grid[i][j] = '0';
            for(int k = 0; k < 4; k++) {
                int x = i + directions[k][0];
                int y = j + directions[k][1];
                if(x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1 || grid[x][y] == '0') {
                    continue;
                }
                findIsland(grid, x, y);
            }
        }
    }
}

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
                if(grid[i][j] == true) { //如果是真, 就开始找岛
                    FindIsland(grid, i, j);
                    count++; //知道开始找了, 就一定有一个岛
                }
            }
        }
        
        return count;
    }
    
    private void FindIsland(boolean[][] grid, int i, int j) {
        if(grid[i][j] == true) {
            grid[i][j] = false; //把走过的真改为假, 这样再扫描的时候这些走过的点就不会再参与考量了
            if(i > 0 && grid[i - 1][j] == true) { //向4个方向进行递归, 注意不能有else
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


//leetcode char[][]grid接口
public class Solution {
  
    //dfs
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') { //如果是真, 就开始找岛
                    FindIsland(grid, i, j);
                    count++; //知道开始找了, 就一定有一个岛
                }
            }
        }
        
        return count;
    }
    
    private void FindIsland(char[][] grid, int i, int j) {
        if(grid[i][j] == '1') {
            grid[i][j] = '0'; //把走过的真改为假, 这样再扫描的时候这些走过的点就不会再参与考量了
            if(i > 0 && grid[i - 1][j] == '1') { //向4个方向进行递归, 注意不能有else
                FindIsland(grid, i - 1, j);
            } if(i < grid.length - 1 && grid[i + 1][j] == '1') {
                FindIsland(grid, i + 1, j);
            } if(j > 0 && grid[i][j - 1] == '1') {
                FindIsland(grid, i, j - 1);
            } if(j < grid[0].length - 1 && grid[i][j + 1] == '1') {
                FindIsland(grid, i, j + 1);
            } 
        }
    }
  
    //bfs
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int result = 0;
        Queue<Pair> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    grid[i][j] = '0';
                    queue.offer(new Pair(i, j));
                    numIslandsHelper(grid, queue);
                    result++;
                }
            }
        }
        return result;
    }
    
    public void numIslandsHelper(char[][] grid, Queue<Pair> queue) {
        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            int i = p.x;
            int j = p.y;
            if(i > 0 && grid[i - 1][j] == '1') { //向4个方向进行递归, 注意不能有else
                queue.offer(new Pair(i-1, j));
                grid[i-1][j] = '0'; //此处置0很关键, 主要是速度问题, 马上改为0, 这样其他点从queue中poll出来的时候再扫描就能马上遇到改过的0
                //加快速度
            } if(i < grid.length - 1 && grid[i + 1][j] == '1') {
                queue.offer(new Pair(i + 1, j));
                grid[i+1][j] = '0';
            } if(j > 0 && grid[i][j - 1] == '1') {
                queue.offer(new Pair(i, j - 1));
                grid[i][j-1] = '0';
            } if(j < grid[0].length - 1 && grid[i][j + 1] == '1') {
                queue.offer(new Pair(i, j + 1));
                grid[i][j+1] = '0';
            } 
        }
    }
    
    class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
