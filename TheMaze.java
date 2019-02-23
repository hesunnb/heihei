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
        queue.offer(start);
        maze[start[0]][start[1]] = 2;
        while(!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            int x = curPoint[0], y = curPoint[1];
            for(int i = 0; i < 4; i++) {
                int xx = x;
                int yy = y;
                while(isValid(maze, xx + dx[i], yy + dy[i])) { //从while循环退出就是撞到墙了
                    xx += dx[i];
                    yy += dy[i];
                }
                if(!isValid(maze, xx, yy) || maze[xx][yy] == 2) { //撞墙节点是否有效
                    continue;
                }
                if(xx == destination[0] && yy == destination[1]) { //因为是撞墙节点, 所以判断destination
                    return true;
                }
                queue.offer(new int[] {xx, yy}); //每次加入到queue中的节点都是撞墙的节点
                maze[xx][yy] = 2;
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
