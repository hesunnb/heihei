/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen 
and an empty space respectively.

Example

There exist two distinct solutions to the 4-queens puzzle:

[
  // Solution 1
  [".Q..",
   "...Q",
   "Q...",
   "..Q."
  ],
  // Solution 2
  ["..Q.",
   "Q...",
   "...Q",
   ".Q.."
  ]
]
*/

class Solution {
    /**
     * Get all distinct N-Queen solutions
     * @param n: The number of queens
     * @return: All distinct solutions
     * For example, A string '...Q' shows a queen on forth position
     */
     
    //用一维数组解决, 下标是行, 值是列
    //时间复杂度: O(n!) 第一行n, 第二行大概n - 1, 大三行大概n - 2...  n!
    //换一种理解方式就是求出n的所有permutations, 然后在所有的permutations里面找出符合nqueens规则的permutation
    List<List<String>> solveNQueens(int n) {
        // write your code here
        
        List<List<String>> result = new ArrayList<List<String>>();
        if(n <= 0) {
            return result;
        }
        
        int[] row = new int[n];
        solveNQueensCore(row, 0, n, result);
        return result;
    }
    
    private void solveNQueensCore(int[] row, int index, int n, List<List<String>> result) { //index每次要访问的行
        if(index == n) {
            List<String> singleResult = translateString(row);
            result.add(singleResult);
            return;
        }
        
        for(int i = 0; i < n; i++) { //每次扫一列, 一列一列的扫
            if(isValid(row, index, i)) { //横坐标是index, 纵坐标是i这个点如果有效
                row[index] = i; //在横坐标的地方记录纵坐标, 用一个数组完成二维数组记录有效位置的功能
                solveNQueensCore(row, index + 1, n, result); //当前行如果该位置有效, 就去找下一行的有效位置(下一行就是index + 1)
                row[index] = 0; //solveNQueensCore返回之后, 说明当前点的所有可能结果已经递归完, 这时候要把当前点的位置清0, 
                //然后继续扫描当前行的下一个点; 其实不清0也可以, 因为isValid每次都判断index前面的值, 而这些值都是有效的
            }
        }
    }
    
    private List<String> translateString(int[] row) {
        List<String> temp = new ArrayList<String>();
        for(int i = 0; i < row.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < row.length; j++) {
                if(row[i] == j) { //有效点
                    sb.append("Q");
                } else { //无效点
                    sb.append(".");
                }
            }
            temp.add(sb.toString());
        }
        return temp;
    }
    
    private boolean isValid(int[] row, int rowNum, int colNum) {
        for(int i = 0; i < rowNum; i++) { //比如传过来(3,1)这个点, rowNum=3, colNum=1, 那么就要看看rowNum在row这个数组里面前方的位置列的情况, 
        //因为在row中rowNum前方存下的位置都是有效位置
            if(row[i] == colNum) {
                return false; //比如i=0,row[i]=1,说明在[0,1]这个位置已经有皇后了, 所以(3,1)就无效, 列不能重复, 就返回假
                //因为是每行每行扫, 所以行不会存在重复, 正在扫描的要放值的这行现在是没有值的, 所以不用判断行重复
            }
            if(Math.abs(i - rowNum) == Math.abs(row[i] - colNum)) { //判断斜着的行有没有重复, 行行与列列的差值如果相等就说明在同一个斜着
            //的方向上, 包括左斜和右斜
                return false;
            }
        }
        return true;
    }
};
