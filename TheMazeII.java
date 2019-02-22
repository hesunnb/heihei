/*There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but 
it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination 
(included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the 
maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example
Example 1:
	Input:  
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (4,4)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Output:  12
	
	Explanation:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)

Example 2:
	Input:
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (0,0)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Output:  6
	
	Explanation:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(0,0)
	
Notice
1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze 
are all walls.
4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.*/

public class Solution {
     
    //与1问不同的地方就是这次得找所有有效路径, 并求出最短路径
    int[] dx = new int[] {-1,1,0,0};
    int[] dy = new int[] {0,0,-1,1};
    int min = Integer.MAX_VALUE;
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || start.length == 0 || destination == null || 
           destination.length == 0) {
            return -1;
        }
        
        hasPathHelper(maze, start[0], start[1], destination, 0);
        if(min == Integer.MAX_VALUE) return -1; //如果没有有效路径, 就返回-1
        return min;
    }
    
    public void hasPathHelper(int[][] maze, int x, int y, int[] destination, int count) {
        if(!isValid(maze, x, y) || maze[x][y] == 2) {
            return;
        }
        
        if(x == destination[0] && y == destination[1]) { 
            min = Math.min(count, min);
            return;
        }
        
        int oriCount = count; //保留每个点出发前的原始count
        maze[x][y] = 2;
        for(int i = 0; i < 4; i++) { 
            count = oriCount; //每个方向走完之后要复位, 从新计算别的方向
            int xx = x;
            int yy = y;
            while(isValid(maze, xx + dx[i], yy + dy[i])) { 
                xx += dx[i];
                yy += dy[i];
                count++;
            }
            hasPathHelper(maze, xx, yy, destination, count);
        }
        maze[x][y] = 0; //重点是这里, maze[x][y] = 2就是设路障, 在本次递归到destination之前, 这些撞过的点就不能再撞了,
        //但是因为这次要找所有能到destination的路径, 所以这次递归设置的路障在回溯的时候要清除, 以便别的路径通过并重新
        //设路障
    }
    
    public boolean isValid(int[][] maze, int x, int y) {
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }
        return true;
    }
}
