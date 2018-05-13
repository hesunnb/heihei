/*Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?*/

class Solution {
    
    /*这道题中说的空间复杂度为O(mn)的解法自不用多说, 直接新建一个和matrix等大小的矩阵, 然后一行一行的扫, 只要有0, 就将新建的矩阵的对应行全赋0, 
    行扫完再扫列, 然后把更新完的矩阵赋给matrix即可, 这个算法的空间复杂度太高. 将其优化到O(m+n)的方法是, 用一个长度为m的一维数组记录各行中是否有0, 
    用一个长度为n的一维数组记录各列中是否有0, 最后直接更新matrix数组即可*/
    //要inplace, O(mn)时间, O(1)空间
    //solution1: 用第一行和第一列存放0来标志这行这列是否应该全被置0
    public void setZeroes(int[][] matrix) {
    
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        boolean emptyRow0 = false;
        boolean emptyCol0 = false;
        int m = matrix.length;
        int n = matrix[0].length;
        //0行0列要先扫描一遍, 来确定本身是否应该被置0, 因为成为标志之后被改变啦, 不好判断啦, 所以改变之前要先扫一遍判断一下
        for(int i = 0; i < n; i++) {
            if(matrix[0][i] == 0) {
                emptyRow0 = true; //先扫描第一行，如果有0，说明最后第一行应该全是0
                break;
            }
        }
        
        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) {
                emptyCol0 = true; //扫描第0列，如果有0，说明最后第一列也应为0
            }
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) { //如果标志是0，就赋值为0
                    matrix[i][j] = 0;
                }
            }
        }
        
        if(emptyRow0) { //最后把0行0列进行赋0值处理
            for(int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        
        if(emptyCol0) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
  
    
    //solution2: 稍微精简一些, 把最开始的3个for合到一起了, 复杂度和顶上的一样, 都是需要扫描两遍矩阵
    public void setZeroes(int[][] matrix) {
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        
        boolean emptyRow0 = false;
        boolean emptyCol0 = false;
        int m = matrix.length;
        int n = matrix[0].length;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) { //前提条件是这个元素是0
                    if(i == 0) emptyRow0 = true; //如果这个0元素出现在第一行, 那么第一行就都要为0
                    if(j == 0) emptyCol0 = true; //如果这个0元素出现在第一列, 那么第一列就都要为0
                    matrix[0][j] = 0; //其余的时候把第一行和第一列置0, 作为接下来清0的标志
                    matrix[i][0] = 0;
                }
            }
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) { //如果标志是0，就赋值为0
                    matrix[i][j] = 0;
                }
            }
        }
        
        if(emptyRow0) { //最后把0行0列进行赋0值处理
            for(int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        
        if(emptyCol0) {
            for(int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
