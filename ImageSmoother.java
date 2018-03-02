/*Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each 
cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding 
cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].*/


class Solution {

    //discuss还有o(1)解法, 不过是c的
 
    //solution2:(own)就是最简单的把周围的点的值都加上然后计算
    public int[][] imageSmoother(int[][] M) {
        
        if(M == null || M.length == 0 || M[0].length == 0) {
            return null;
        }
        
        int[][] result = new int[M.length][M[0].length];
        
        for(int i = 0; i < M.length; i++) {
            for(int j = 0; j < M[0].length; j++) {
                double sum = M[i][j]; //自己的值也要算上
                double num = 1;
                if(i > 0) { //上面三个
                    sum += M[i-1][j];
                    num++;
                }
                if(i > 0 && j > 0) {
                    sum += M[i-1][j-1];
                    num++;
                }
                if(i > 0 && j < M[0].length - 1) {
                    sum += M[i-1][j+1];
                    num++;
                }
                if(j > 0) { //左右两个
                    sum += M[i][j-1];
                    num++;
                }
                if(j < M[0].length - 1) {
                    sum += M[i][j+1];
                    num++;
                }
                if(i < M.length - 1) { //下面三个
                    sum += M[i+1][j];
                    num++;
                }
                if(i < M.length - 1 && j > 0) {
                    sum += M[i+1][j-1];
                    num++;
                }
                if(i < M.length - 1 && j < M[0].length - 1) {
                    sum += M[i+1][j+1];
                    num++;
                }
                result[i][j] = (int)Math.floor(sum / num);
            }
        }

        return result;
    }
}
