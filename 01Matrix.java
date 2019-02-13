/*Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.*/

class Solution {
    
    /*Using DFS method.

    Assigned a large value to all the positions with value 1 and don't have 0 neighbors
    Start dfs search from positions whose value is 1*/
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix.length==0) {
            return matrix;
        }
        
        for(int i = 0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[0].length; j++) {
                if(matrix[i][j]==1&&!hasNeiberZero(i, j,matrix)) {
                    matrix[i][j] = Integer.MAX_VALUE; //更改为最大特别有必要, dfs就在于传的val一直都在加, 而要求最短距离是一个
                    //取小值的过程, 所以从最大往回走
                }   
            }
        }
            
        for(int i = 0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[0].length; j++) {
                if(matrix[i][j]==1) {
                    dfs(matrix, i, j, -1);
                }
            }   
        }
        
        return matrix;
    }
    private void dfs(int[][] matrix, int x, int y, int val){
        if(x<0||y<0||y>=matrix[0].length||x>=matrix.length||matrix[x][y]<=val) {
            return;
        }
            
        if(val>0) { //只对于递归开始的第一次有效, 比如example1, 传入-1不大于0所以不更改值, 如果不加这个判断, 那么1就被直接改为-1了
            matrix[x][y] = val;
        }
        
        dfs(matrix, x+1, y, matrix[x][y]+1); //下
        dfs(matrix, x-1, y, matrix[x][y]+1); //上
        dfs(matrix, x, y+1, matrix[x][y]+1); //右
        dfs(matrix, x, y-1, matrix[x][y]+1); //左
    }
    
    private boolean hasNeiberZero(int x, int y, int[][] matrix){
        if(x>0&&matrix[x-1][y]==0) return true;
        if(x<matrix.length-1&&matrix[x+1][y]==0) return true;
        if(y>0&&matrix[x][y-1]==0) return true;
        if(y<matrix[0].length-1&&matrix[x][y+1]==0) return true;
        
        return false;
    }
    
    
    /*General idea is BFS. Some small tricks:

    At beginning, set cell value to Integer.MAX_VALUE if it is not 0.
    If newly calculated distance >= current distance, then we don't need to explore that cell again.*/
    //这个BFS方法很好, 不从1出发, 反向从0出发开始找, 把1都变为Integer.MAX_VALUE, 然后BFS, 值得学习
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j}); //把是0的点加入队列
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE; //非0值点设为Integer.MAX_VALUE
                }
            }
        }
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //上, 下, 左, 右
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll(); //拿出一个cell
            for (int[] d : dirs) { //每一个cell都要向4个方向走出确切的一步
                int r = cell[0] + d[0];
                int c = cell[1] + d[1]; //走出一步之后的坐标
                if (r < 0 || r >= m || c < 0 || c >= n || 
                    matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) {
                    continue; //如果新的坐标越界或者新计算的距离比之前的结果要大, 那么就continue
                }
                queue.add(new int[] {r, c}); //如果新的坐标距离更短, 那么就加入队列
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1; //更新为新的距离
            }
        }
        
        return matrix;
    }
}
