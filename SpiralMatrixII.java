/*Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]*/

class Solution {
    public int[][] generateMatrix(int n) {
        
        int[][] result = new int[n][n];
        
        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;
        
        int num = 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                result[rowBegin][j] = num++;
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                result[j][colEnd] = num++;
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) { //discuss里面说唯一不好的地方就是要在这里判断一下rowBegin <= rowEnd, 和下面colBegin <= colEnd
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    result[rowEnd][j] = num++;
                }
            }
            rowEnd--;
            
            if (colBegin <= colEnd) { //n*n的正方形矩阵, 这两个判断用不上, 但是像上面的m*n的矩形矩阵就会用上这两个判断了
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    result[j][colBegin] = num++;
                }
            }
            colBegin++;
        }
        
        return result;
    }
}
