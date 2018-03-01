/*In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep 
its original data.

You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column 
number of the wanted reshaped matrix, respectively.

The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.

If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original 
matrix.

Example 1:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 1, c = 4
Output: 
[[1,2,3,4]]
Explanation:
The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
Example 2:
Input: 
nums = 
[[1,2],
 [3,4]]
r = 2, c = 4
Output: 
[[1,2],
 [3,4]]
Explanation:
There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
Note:
The height and width of the given matrix is in range [1, 100].
The given r and c are all positive.*/


class Solution {
 
    //solution1:
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        
        if(nums == null || nums.length == 0 || (nums.length*nums[0].length) != (r*c) || r <= 0 || c <= 0) {
            return nums;
        }
        
        int m = nums[0].length;
        int[][] result = new int[r][c];
        
        for (int i = 0; i < r*c; i++) {
            result[i/c][i%c] = nums[i/m][i%m]; //各自矩阵对各自的列求余就可以了, 因为此时这两个矩阵的数目元素一定是一样的
        }
        
        return result;
    }
 
    
    //solution2:(own)
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        
        if(nums == null || nums.length == 0 || (nums.length*nums[0].length) != (r*c) || r <= 0 || c <= 0) {
            return nums;
        }
        
        int len = 0;
        int width = 0;
        int[][] result = new int[r][c];
        
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(width < nums[0].length) { //按照宽走
                    result[i][j] = nums[len][width];
                    width++;
                } else { //宽走到头了, 行+1, 宽清零
                    len++;
                    width = 0;
                    result[i][j] = nums[len][width]; //然后赋值
                    width++; //宽向后走一位, 赋值和宽向后走一位一定要跟上, 要不只更改了行数和列数而没有赋值就不对了
                }
            }
        }
        return result;
    }
}
