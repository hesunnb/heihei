/*There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the 
maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example
Given:
a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

start coordinate (rowStart, colStart) = (0, 4)
destination coordinate (rowDest, colDest) = (4, 4)

Return:true
Notice
1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the 
maze are all walls.
5.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.*/

public class Solution {

    //bfs解法, 迷宫一般都用bfs解
    int[] dx = new int[] {-1,1,0,0};
    int[] dy = new int[] {0,0,-1,1};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || start.length == 0 || destination == null || 
           destination.length == 0) {
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]});

        while(!queue.isEmpty()) {

            int[] curPoint = queue.poll();
            int x = curPoint[0];
            int y = curPoint[1];
            if(x == destination[0] && y == destination[1]) {
                return true;
            }
            if(maze[x][y] == 2) {
                continue;
            }
            maze[x][y] = 2;
            for(int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                while(isValid(maze, nx + dx[i], ny + dy[i])) { //从while循环退出就是撞到墙了
                    nx += dx[i];
                    ny += dy[i];
                }
                queue.offer(new int[] {nx, ny}); //每次加入到queue中的节点都是撞墙节点, 当上下左右方向不对的时候,
                //这里会把撞墙节点重复加入, 但是弹出撞墙节点的时候已经把它标记为2了, 所以重复加入这个撞墙节点的时候
                //再弹出就都是2, 全都continue了
            }
        }
        
        return false;
    }
    
    public boolean isValid(int[][] maze, int x, int y) {
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }
        return true;
    }
    
    
    //boolean[][] visited也可以没有, 省空间, 用其他值替代一下就可以了, 比如这里用2来代表在此处撞过墙
    int[] dx = new int[]{-1,1,0,0};
    int[] dy = new int[]{0,0,-1,1};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        
        if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || start.length == 0 || destination == null || 
           destination.length == 0) {
            return false;
        }
        
        return hasPathHelper(maze, start[0], start[1], destination);
    }
    
    public boolean hasPathHelper(int[][] maze, int x, int y, int[] destination) {
        if(!isValid(maze, x, y) || maze[x][y] == 2) {
            return false;
        }
        
        if(x == destination[0] && y == destination[1]) { //还有就是这道题最终判断的是小球能不能停在destination, 
            //也就是小球在destination处一定是撞墙状态, 路过destination不算数的
            return true;
        }
        
        maze[x][y] = 2;
        for(int i = 0; i < 4; i++) { //4个方向, 只有撞墙了才能改变方向, 途中不能改变方向
            int xx = x;
            int yy = y;
            while(isValid(maze, xx + dx[i], yy + dy[i])) { //重点是这里, 向某一个方向走不停的处理
                xx += dx[i];
                yy += dy[i]; //isValid和visited是不同的东西, 只有撞墙停下了才会去考虑visited, 向某一个方向走不停的时候
                //不会考虑visited, 这个一定注意
            }
            if(hasPathHelper(maze, xx, yy, destination)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isValid(int[][] maze, int x, int y) {
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }
        return true;
    }
    
    
    //用boolean[][] visited数组
    int[] dx = new int[] {-1,1,0,0};
    int[] dy = new int[] {0,0,-1,1};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        
        if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || start.length == 0 || destination == null || 
           destination.length == 0) {
            return false;
        }
        
        return hasPathHelper(maze, start[0], start[1], destination, new boolean[maze.length][maze[0].length]);
    }
    
    public boolean hasPathHelper(int[][] maze, int x, int y, int[] destination, boolean[][] visited) {
        if(!isValid(maze, x, y) || visited[x][y]) {
            return false;
        }
        
        if(x == destination[0] && y == destination[1]) { //还有就是这道题最终判断的是小球能不能停在destination, 
            //也就是小球在destination处一定是撞墙状态, 路过destination不算数的
            return true;
        }
        
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) { //4个方向
            int xx = x;
            int yy = y;
            while(isValid(maze, xx + dx[i], yy + dy[i])) { //重点是这里, 向某一个方向走不停的处理
                xx += dx[i];
                yy += dy[i]; //isValid和visited是不同的东西, 只有撞墙停下了才会去考虑visited, 向某一个方向走不停的时候
                //不会考虑visited, 这个一定注意
            }
            if(hasPathHelper(maze, xx, yy, destination, visited)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isValid(int[][] maze, int x, int y) {
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }
        return true;
    }
}
