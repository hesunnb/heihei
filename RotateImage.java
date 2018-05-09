public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    
    //solution1: 只操作矩阵一遍
    //因为是n*n, 所以才让do-inplace, 长宽不等没法inplace
    //思路就是记住是整个图像旋转，不是窜，是旋转
    public void rotate(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return; //虽然函数没有返回值，但是意思就是结束，不向下进行啦
        }
        
        int length = matrix.length;
        for(int i = 0; i < length / 2; i++) {
            for(int j = 0; j < (length + 1) / 2; j++) { //取循环的位置
                int temp = matrix[i][j]; //取数旋转，旋转4回，依次填充相应位置
                matrix[i][j] = matrix[length - j - 1][i]; //注意是先j后i
                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = temp;
            }
        }
    }
    
    /**
     *   1 2  i = 0, j = 0; [0, 0]拿出去, 然后转圈
     *   3 4
     * 
     *   1 2 3  i = 0, j = 0,1 取1,2旋转
     *   4 5 6
     *   7 8 9
     * 
     *   1 2 3 4  i = 0,1 j = 0,1 取1,2,5,6旋转
     *   5 6 7 8
     *   9 10 11 12
     *   13 14 15 16
     * 
     *   1 2 3 4 5  i = 0,1 j = 0,1,2 取1,2,3,6,7,8旋转
     *   6 7 8 9 10
     *   11 12 13 14 15
     *   16 17 18 19 20
     *   21 22 23 24 25
     **/
    
    
    //solution2: 操作矩阵两遍, common ways
    /*
     * clockwise rotate
     * first reverse up to down, then swap the symmetry 
     * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
    */
    
    /*
     * anticlockwise rotate
     * first reverse left to right, then swap the symmetry
     * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
    */
    public void rotate(int[][] matrix) {
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return; //虽然函数没有返回值，但是意思就是结束，不向下进行啦
        }
        
        for(int i = 0; i < matrix.length / 2; i++) { //按照顶上的图, 先上下颠倒
            for(int j = 0; j < matrix[0].length; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-i][j];
                matrix[matrix.length-1-i][j] = temp;
            }
        }
        
        for(int i = 0; i < matrix.length; i++) { //再转置
            for(int j = i; j < matrix[0].length; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
