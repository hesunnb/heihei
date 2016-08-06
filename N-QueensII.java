/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Example

For n=4, there are 2 distinct solutions.
*/

class Solution {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        //write your code here
        
        if(n <= 0) {
            return 0;
        }
        
        int count = 0;
        int[] row = new int[n];
        count = totalNQueensCore(row, 0, n, count);
        return count;
    }
    
    private int totalNQueensCore(int[] row, int index, int n, int count) { //index每次要访问的行
        if(index == n) {
            count++;
            return count;
        }
        
        for(int i = 0; i < n; i++) { //每次扫一行, 一行一行的扫
            if(isValid(row, index, i)) { //横坐标是index, 纵坐标是i这个点如果有效
                row[index] = i; //在横坐标的地方记录纵坐标, 用一个数组完成二维数组记录有效位置的功能
                count = totalNQueensCore(row, index + 1, n, count); //当前行如果该位置有效, 就去找下一行的有效位置(下一行就是index + 1)
                row[index] = 0; //solveNQueensCore返回之后, 说明当前点的所有可能结果已经递归完, 这时候要把当前点的位置清0, 然后继续扫描当前行的下一个点
            }
        }
        return count;
    }
    
    private boolean isValid(int[] row, int rowNum, int colNum) {
        for(int i = 0; i < rowNum; i++) { //比如传过来(3,1)这个点, rowNum=3, colNum=1, 那么就要看看rowNum在row这个数组里面前方的位置列的情况, 因为在row中rowNum前方存下的位置都是有效位置
            if(row[i] == colNum) {
                return false; //比如i=0,row[i]=1,说明在[0,1]这个位置已经有皇后了, 所以(3,1)就无效, 列不能重复, 就返回假
                //因为是每行每行扫, 所以行不会存在重复, 正在扫描的要放值的这行现在是没有值的, 所以不用判断行重复
            }
            if(Math.abs(i - rowNum) == Math.abs(row[i] - colNum)) { //判断斜着的行有没有重复, 行行与列列的差值如果相等就说明在同一个斜着的方向上, 包括左斜和右斜
                return false;
            }
        }
        return true;
    }
};

