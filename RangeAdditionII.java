/*Given an m * n matrix M initialized with all 0's and several update operations.

Operations are represented by a 2D array, and each operation is represented by an array with two positive integers a and b, which means 
M[i][j] should be added by one for all 0 <= i < a and 0 <= j < b.

You need to count and return the number of maximum integers in the matrix after performing all the operations.

Example 1:
Input: 
m = 3, n = 3
operations = [[2,2],[3,3]]
Output: 4
Explanation: 
Initially, M = 
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

After performing [2,2], M = 
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

After performing [3,3], M = 
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

So the maximum integer in M is 2, and there are four of it in M. So return 4.
Note:
The range of m and n is [1,40000].
The range of a is [1,m], and the range of b is [1,n].
The range of operations size won't exceed 10,000.*/

class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        
        if(m <= 0 || n <= 0 || ops == null || ops.length == 0 || ops[0].length == 0) {
            return m * n; //这道题m,n就在这里用到了...对于解题m,n没有作用, 潜在要求返回m*n, 返回别的值不对
        }
        
        //只需要在ops中找行最小和列最小即可, 因为行最小*列最小这部分元素不管谁更新都会更新这部分, 所以行最小*列最小正好是元素个数, 返回即可
        int row = Integer.MAX_VALUE, col = Integer.MAX_VALUE;
        for(int[] step : ops) {
            row = Math.min(row, step[0]); //行最小
            col = Math.min(col, step[1]); //列最小
        }
        return row * col;
    }
}
