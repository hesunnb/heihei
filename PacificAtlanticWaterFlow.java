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
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }
    
    public boolean helper(boolean[] left, boolean[] right, int x, int y, int[][] matrix, boolean[][] canReach) {
	if(canReach[x][y]) {
            return true;
        }
        if(x == 0 || y == 0) {
            left[0] = true;
        }
        if(x == matrix.length - 1 || y == matrix[0].length - 1) {
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
}
