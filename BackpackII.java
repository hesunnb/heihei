/*Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Challenge 
O(n x m) memory is acceptable, can you do it in O(m) memory?*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A & V: Given n items with size A[i] and value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        
        if(A == null || A.length == 0) {
            return 0;
        }
        int[] pack = new int[m + 1]; //下标就是背包的个数
        for(int i = 0; i < A.length; i++){ //以A的个数遍历, 不用排序, 因为先装哪个值都一样, 都是先装好一个, 再装其他的
            for(int j = m; j >= A[i]; j--){ //从后向前看包的个数
                pack[j] = Math.max(pack[j], V[i] + pack[j - A[i]]); //要装V[i]的钱数, 1问里面装的是体积, 这道题向里面装钱数就可以了
            }
        }
        return pack[m];
    }
}
