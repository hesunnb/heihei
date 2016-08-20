/*Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.*/

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
     
    //三维数组, 用动归解, 复杂度就是O(A.length*k*target)
    public int kSum(int A[], int k, int target) {
        // write your code here
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        //k作为第三维纵轴, 表示有多少层, i和j表示平面二维
        int[][][] count = new int[A.length][target][k];
        for(int i = 0; i < count.length; i++) {
            for(int j = 0; j < count[0].length; j++) {
                if (j + 1 == A[i]) { //j是下标, 对应的数要加1
                    for (int h = i; h < count.length; h++){
                        count[h][j][0] = 1; //让第一层j + 1位置的数从上到下都是1
                    }
                }
            }
        }
        
        for(int h = 1; h < k; h++) { //从第二层开始到第k层, 每层都是基于上一层进行计算
            for (int i = h; i < count.length; i++){ //第一层从第一行开始, 第二层从第二行开始, 第i层从第i行开始
                for (int j = 0; j < count[0].length; j++){
                    count[i][j][h] = count[i - 1][j][h]; //先将上一层求得的结果抄下来
                }
                int m = 0;
                while(A[i] + m + 1 <= target){ //(A[i] + m) + 1 下标值+1就是指了, 值小于等于target的时候就进行循环
                    count[i][A[i] + m][h] += count[i - 1][m][h - 1]; //A[i] + m又恢复成了下标, 不加1
                    //当前层A[i] + m位置的值要 += 上一层上一行m位置的值
                    m++;
                }
                
            }
        }
        
        return count[A.length - 1][target - 1][k - 1]; //最右下角是最终结果
    }
    
    /*
    testCase: [1,2,3,4,5,6,7,8,9] target = 9 //这个题里都是正数没有0
      1 2 3 4 5 6 7 8 9 
    2 0 1 0 0 0 0 0 0 0 //第一层第1行表示只有2的时候只用一个数
    3 0 1 1 0 0 0 0 0 0 //第一层第2行表示有2,3的时候只用一个数
    6 0 1 1 0 0 1 0 0 0 //第一层第3行表示有2,3,6的时候只用一个数
    7 0 1 1 0 0 1 1 0 0 //第一层第4行表示有2,3,6,7的时候只用一个数
    
      1 2 3 4 5 6 7 8 9
    2 0 0 0 0 0 0 0 0 0 //第二层第1行表示只有2的时候用二个数(只有2, 还要用2个肯定没结果)
    3 0 0 0 0 1 0 0 0 0 //第二层第2行表示有2,3的时候用二个数(3与2进行结合, 因为2的第一层有数, 所以能够拼出5)
    6 0 0 0 0 1 0 0 1 1 //第二层第3行表示有2,3,6的时候用二个数(同理, 6与2,3(第一层)结合能够拼出8,9)
    7 0 0 0 0 1 0 0 1 2 //第二层第4行表示有2,3,6,7的时候用二个数(7与2(第一层)结合能够拼出9)
    */
}
