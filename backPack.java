/*Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

 Notice

You can not divide any item into small pieces.

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.

Challenge 
O(n x m) time and O(m) memory.

O(n x m) memory is also acceptable if you do not know how to optimize memory.*/

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
     
    //只用一个一维数组, 因为每次只用得上前一行的数据, 所以直接覆盖掉了
    public int backPack(int m, int[] A) {
        // write your code here
        
        if(A == null || A.length == 0) {
            return 0;
        }
        int[] pack = new int[m + 1]; //下标就是背包的个数
        for(int i = 0; i < A.length; i++){ //以A的个数遍历, 不用排序, 因为先装哪个值都一样, 都是先装好一个, 再装其他的
            for(int j = m; j >= A[i]; j--){ //从后向前看包的个数
                pack[j] = Math.max(pack[j], A[i] + pack[j - A[i]]); //我要装A[i]的包数, 除了A[i](j - A[i])之外剩余的包数它之前装的是什么值加上, 再和j处原来的包数比大小, 存下大的数
            }
        }
        return pack[m];
    }
    
    /*
    testCase:
      0 1 2 3 4 5 6 7 8 9
    3 0 0 0 3 3 3 3 3 3 3
    4 0 0 0 3 4 4 4 7 7 7
    5 0 0 0 3 4 5 5 7 8 9
    8 0 0 0 3 4 5 5 7 8 9
    */
    
    //用二维数组
    public int backPack(int m, int[] A) {
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int[][] pack = new int[A.length][m + 1];
        
        for(int i = 0; i < A[0]; i++) { //从0到A[0]给0, A[0]后面给A[0]
            if (i < m + 1){ //如果越界了(A[0]比m + 1大), m + 1之前全是0, 之后的就什么都不做了
                pack[0][i] = 0;
            }
        }
        
        for(int i = A[0]; i < m + 1; i++) { //A[0]后面给A[0]
            pack[0][i] = A[0];
        }
        
        for(int i = 1; i < A.length; i++) { 
            for(int j = m; j >= A[i]; j--) {
                pack[i][j] = Math.max(pack[i - 1][j], A[i] + pack[i - 1][j - A[i]]);
            }
            for(int j = 0; j < A[i]; j++) { //不计算的地方照抄
                if (j < m + 1){ //越界判断, 如果越界了就全部照抄, 没越界就抄到没越界的地方
                     pack[i][j] = pack[i - 1][j];
                }
            }
        }
        return pack[A.length - 1][m];
    }
}
