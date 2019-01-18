/*Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and 
right-down number.

Example
Given matrix

[
  [1 ,5 ,7],
  [3 ,7 ,-8],
  [4 ,-8 ,9],
]
return [(1,1), (2,2)]

Challenge
O(n3) time.*/

public class Solution {
 
    public int[][] submatrixSum(int[][] matrix) {
        // write your code here
        int[][] result = new int[2][2];
        int M = matrix.length;
        if (M == 0) return result;
        int N = matrix[0].length;
        if (N == 0) return result;
        // pre-compute: sum[i][j] = sum of submatrix [(0, 0), (i, j)]
        int[][] sum = new int[M+1][N+1]; //创建一个pre-sum的矩阵, 减少重复计算, 否则就是O(n^4)复杂度了
        for (int j=0; j<=N; ++j) sum[0][j] = 0;
        for (int i=1; i<=M; ++i) sum[i][0] = 0;
        for (int i=0; i<M; ++i) {
            for (int j=0; j<N; ++j)
                sum[i+1][j+1] = matrix[i][j] + sum[i+1][j] + sum[i][j+1] - sum[i][j];
        }
        for (int l=0; l<M; ++l) { //从这里开始就只用sum矩阵的值了, 这个是基准行
            for (int h=l+1; h<=M; ++h) { //用基准行下面的每一行减去基准行, 得到的diff都是包含基准行元素的所有可能矩阵的值
                Map<Integer, Integer> map = new HashMap<Integer, Integer>(); //每从新计算一行就会用一个新的哈希表
                for (int j=0; j<=N; ++j) { //在确定完行的基础上遍历每一列
                    int diff = sum[h][j] - sum[l][j];
                    if (map.containsKey(diff)) { //如果找到相同diff, 说明一个子矩阵的和就是0
                        int k = map.get(diff);
                        result[0][0] = l;   result[0][1] = k;
                        result[1][0] = h-1; result[1][1] = j-1;
                        return result;
                    } else {
                        map.put(diff, j);
                    }
                }
            }
        }
        return result;
    }
}
