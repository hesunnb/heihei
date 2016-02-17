public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    
    //思路就是记住是整个图像旋转，不是窜，是旋转
    public void rotate(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return; //虽然函数没有返回值，但是意思就是结束，不向下进行啦
        }
        
        int length = matrix.length;
        for(int i = 0; i < length / 2; i++)
        {
            for(int j = 0; j < (length + 1) / 2; j++) //取循环的位置
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][i]; //注意是先j后i
                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = temp;
            }
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
