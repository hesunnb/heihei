public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @return: an array of integers
     */ 
    public int[] printZMatrix(int[][] matrix) {
        // write your code here
        //要斜着看矩阵就是蛇形啦
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return null;
        }
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int[] result = new int[(m + 1) * (n + 1)];
        int index = 0;
        
        for(int i = 0; i <= m + n; i++) //遍历每行，从0行到m + n行
        {
            if(i % 2 == 0) //坐标和为偶数
            {
                for(int x = i; x >= 0 ; x--) //x坐标从i到0遍历
                {
                    //横纵坐标全都小于所给矩阵的最大下标，把那些不是的坐标都给过滤啦,比如（4,0）（3,1）（2,2）（1,3）（0,4），（4,0）（3,1）（0,4）就没要，不符合矩阵下标
                    if(x <= m && (i - x) <= n) 
                    {
                        result[index] = matrix[x][i - x];
                        index++;
                    }
                }
            }
            else //坐标和为奇数
            {
                for(int x = 0; x <= i; x++) //x坐标从0到i遍历
                {
                    if(x <= m && (i - x) <= n)
                    {
                        result[index] = matrix[x][i - x];
                        index++;
                    }
                }
            }
        }
        return result;
        
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
    }
}
