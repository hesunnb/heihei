/*Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches 
the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).*/

class Solution {
	
    //以后都要用这种dir的方式来缩短代码长度
    //(own), 最普通的dfs
    int[][] dir = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        boolean[][] canReach = new boolean[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(helper(new boolean[1], new boolean[1], i, j, matrix, canReach)) {
                    canReach[i][j] = true;
                    result.add(new int[] {i, j});
                }
            }
        }
        return result;
    }
    
    public boolean helper(boolean[] left, boolean[] right, int x, int y, int[][] matrix, boolean[][] canReach) {
	if(canReach[x][y]) { //canReach就是记忆化搜索, 如果某个位置之前能够到达两边, 那么就记录, 再次到这个点的时候就不用再向下递归了
            return true;
        }
        if(x == 0 || y == 0) { //能够到达左边, 左右用数组的原因是能够记录下之前是否到达过左右两边, 而不受递归传值的影响
            left[0] = true; //值传递的问题就是最开始的点初始化是false, false, 当递归回到这个初始点继续向别的方向走的时候就会重新传入
	    //false, false, 而把之前得到的true的结果给丢掉了
        }
        if(x == matrix.length - 1 || y == matrix[0].length - 1) { //能够到达右边
            right[0] = true;
        }
        if(left[0] && right[0]) {
            return true;
        }
        int tmp = matrix[x][y];
        for(int[] d : dir) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if(nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length && matrix[nextX][nextY] <= matrix[x][y]) {
                matrix[x][y] = Integer.MAX_VALUE;
                if(helper(left, right, nextX, nextY, matrix, canReach)) {
                    matrix[x][y] = tmp;
                    return true;
                }
            }
            matrix[x][y] = tmp;
        }
        matrix[x][y] = tmp;
        return false;
    }
	
    
    //dfs discuss: 反其道而行之, 因为把左右边分开了, 每个矩阵负责一边, 所以速度会更快
    int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        boolean[][]pacific = new boolean[n][m];
        boolean[][]atlantic = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0); //从边上能到达的低点出发向中间的高点走, 途中标记能到达的点
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m-1);
        }
        for(int i = 0; i < m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n-1, i);
        }
        for (int i = 0; i < n; i++) { //两个矩阵综合就是都能到达的点
            for (int j = 0; j < m; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[] {i, j});
                }   
            }      
        }
            
        return res;
    }
    
    public void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
        int n = matrix.length, m = matrix[0].length;
        if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height) {
            return;
        }
        visited[x][y] = true;
        for(int[]d : dir){
            dfs(matrix, visited, matrix[x][y], x+d[0], y+d[1]);
        }
    } 
}
