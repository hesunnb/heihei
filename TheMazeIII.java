/*There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) 
or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also 
a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest 
distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the 
hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, 
you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze 
are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example
Input:
a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

ball coordinate (rowBall, colBall) = (4, 3)
hole coordinate (rowHole, colHole) = (0, 1)

Output:"lul"
Notice
1.There is only one ball and one hole in the maze.
2.Both the ball and hole exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze 
are all walls.
4.The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.*/

public class Solution {

    int[] dx = new int[] {-1,1,0,0};
    int[] dy = new int[] {0,0,-1,1};
    char[] dir = {'u','d','l','r'};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // write your code here
        PriorityQueue<Point> queue = new PriorityQueue<Point>(new MazeComparator());
        queue.offer(new Point(ball[0], ball[1], 0, new StringBuilder()));

        while(!queue.isEmpty()) {

            Point curPoint = queue.poll();
            int x = curPoint.x;
            int y = curPoint.y;
            int len = curPoint.length;
            StringBuilder sb = curPoint.sb;
            if(x == hole[0] && y == hole[1]) {
                return sb.toString();
            }
            if(maze[x][y] == 2) { //因为涉及到next的无效判断, 所以这里maze[x][y]判断和赋值的位置以及顺序就不要改了
                continue;
            }
            maze[x][y] = 2;
            for(int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                int newLen = len;
                StringBuilder next = new StringBuilder(sb);
                next.append(dir[i]); //此处不用先判断isValid就直接append方向原因: 从代码可以看出next是每次for循环都要加入
                //到PriorityQueue中, 那比如向上走无效, 然后next也append了'u', 然后把这个无效的next加入到了PriorityQueue中,
                //那怎么回事: 重点就在于虽然next改变了, 但是nx, ny还是之前撞墙时候的坐标, 而撞墙坐标已经被标记为2, 所以
                //无效next加入到PriorityQueue中弹出的时候判断的是nx, ny撞墙点的坐标, 所以直接continue了
                while(isValid(maze, nx + dx[i], ny + dy[i])) { 
                    nx += dx[i];
                    ny += dy[i];
                    newLen++;
                    if(nx == hole[0] && ny == hole[1]) break;
                }
                queue.offer(new Point(nx, ny, newLen, next)); //这道题一定要把到达洞的点加入到PriorityQueue中, 就题目中
                //给的例子, "ul"和"lul"都能用相同步数(6步)从[4,3]到达[0,1], 如stdout显示, 如果加入到PriorityQueue, "lul"会
                //先弹出, 然后就结束了, "ul"留在了PriorityQueue中没有弹出来, 如果在while语句中直接判断遇到洞就返回, 实则
                //会先走出"ul"这个结果, 就直接返回了, 没有得到"lul"这个结果, 所以都要加入PriorityQueue中一起判断
            }
        }
        
        return "impossible";
    }
    
    public boolean isValid(int[][] maze, int x, int y) {
        if(x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1) {
            return false;
        }
        return true;
    }
    
    class Point {
        int x;
        int y;
        int length;
        StringBuilder sb;
        Point(int x, int y, int length, StringBuilder sb) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.sb = sb;
        }
    }
    
    class MazeComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if(a.length == b.length) {
                return a.sb.toString().compareTo(b.sb.toString());
            }
            return a.length - b.length;
        }
    }
}
