/*Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in ZigZag-order.
Given a matrix:

[
  [1, 2,  3,  4],
  [5, 6,  7,  8],
  [9,10, 11, 12]
]

return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]
*/

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @return: an array of integers
     */ 
    public int[] printZMatrix(int[][] matrix) {
        // write your code here
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int size = n + m - 1;
        int[] result = new int[m * n];
        int index = 0;
        
        for(int i = 0; i < size; i++) {
            if(i % 2 == 0) { //奇数行
                for(int j = i; j >= 0; j--) {
                    if(j < m && i - j < n) //横纵坐标全都小于所给矩阵的最大下标，把那些不是的坐标都给过滤啦,比如（4,0）（3,1）（2,2）（1,3）（0,4），（4,0）（3,1）（0,4）就没要，不符合矩阵下标
                    result[index++] = matrix[j][i - j]; //横坐标递减, 纵坐标递增, i是坐标总和
                }
            }
            
            if(i % 2 != 0) { //偶数行
                for(int j = i; j >= 0; j--) {
                    if(i - j < m && j < n)
                    result[index++] = matrix[i - j][j]; //纵坐标递减, 横坐标递增, i是坐标总和
                }
            }
        }
        return result;
    }
}
        
        /**
         *  (0, 0)
            (0, 1), (1, 0)
            (2, 0), (1, 1), (0, 2)
            (0, 3), (1, 2), (2, 1)
            (2, 2), (1, 3)
            (2, 3)
         *  可以发现其中每一行的坐标之和为常数，坐标和为奇数时 x 递增, y递减; 为偶数时(偶数行) x 递减, y递增。
         * 
         */
