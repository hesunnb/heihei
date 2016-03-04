public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
     
    //要inplace，O(mn)时间，O(1)空间
    //用第一行和第一列存放0来标志这行这列是否应该全被置0
    public void setZeroes(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return;
        }
        
        boolean empty_row0 = false;
        boolean empty_col0 = false;
        int m = matrix.length;
        int n = matrix[0].length;
        //0行0列要先扫描一遍，来确定本身是否应该被置0，因为成为标志之后被改变啦，不好判断啦，所以改变之前要先扫一遍判断一下
        for(int i = 0; i < n; i++)
        {
            if(matrix[0][i] == 0)
            {
                empty_row0 = true; //先扫描第一行，如果有0，说明最后第一行应该全是0
                break;
            }
        }
        
        for(int i = 0; i < m; i++)
        {
            if(matrix[i][0] == 0)
            {
                empty_col0 = true; //扫描第0列，如果有0，说明最后第一列也应为0
            }
        }
        
        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
                if(matrix[i][j] == 0)
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) //如果标志是0，就赋值为0
                {
                    matrix[i][j] = 0;
                }
            }
        }
        
        if(empty_row0) //最后把0行0列进行赋0值处理
        {
            for(int i = 0; i < n; i++)
            {
                matrix[0][i] = 0;
            }
        }
        
        if(empty_col0)
        {
            for(int i = 0; i < m; i++)
            {
                matrix[i][0] = 0;
            }
        }
    }
}
