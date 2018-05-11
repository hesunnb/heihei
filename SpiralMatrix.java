/*Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]*/

class Solution {

    //solution1: 稍有问题的就是下面多出来判断那里
    public List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int rowBegin = 0; //用四个位置限制住要遍历的矩阵
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                result.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                result.add(matrix[j][colEnd]);
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) { //discuss里面说唯一不好的地方就是要在这里判断一下rowBegin <= rowEnd, 和下面colBegin <= colEnd
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    result.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;
            
            if (colBegin <= colEnd) { //n*n的正方形矩阵, 这两个判断用不上, 但是像上面的m*n的矩形矩阵就会用上这两个判断了
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    result.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }
        
        return result;
    }
    
    
    //solution2: 没有用solution1的后面两步的判断, 个人感觉solution2更好, 就是while(true)有些突兀
    public List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int rowBegin = 0; //用四个位置限制住要遍历的矩阵
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while(true) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                result.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            if(colBegin > colEnd || rowBegin > rowEnd) break;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                result.add(matrix[j][colEnd]);
            }
            colEnd--;
            if(colBegin > colEnd || rowBegin > rowEnd) break;
            
            // Traverse Left
            for (int j = colEnd; j >= colBegin; j--) {
                result.add(matrix[rowEnd][j]);
            }
            rowEnd--;
            if(colBegin > colEnd || rowBegin > rowEnd) break;
            
            // Traver Up
            for (int j = rowEnd; j >= rowBegin; j--) {
                result.add(matrix[j][colBegin]);
            }
            colBegin ++;
            if(colBegin > colEnd || rowBegin > rowEnd) break;
        }
        
        return result;
    }
}
