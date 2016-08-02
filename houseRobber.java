public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    
    public int rob(int[] nums) {

        int m = 0; //m是n的前一个最大
        int n = 0;
        
        for(int i = 0; i < nums.length; i++) { //对于头两个元素来说, 最开始m是头0大, n变成了头1大; 
        //接着m是头1大, n变成头2大, 往下以此类推
            int temp = n;
            n = Math.max(m + nums[i], n); //假设第三个数来, 就是头1大加上第三个数与头2大做比较成为头3大给n, m变成头2大...
            m = temp;
        }
        return n;
    }
    
    //O(n)time and O(1)memory 九章方法加上自己改进
    public long houseRobber(int[] A) 
    {
        if(A == null || A.length == 0)
        {
            return 0;
        }
        long result[] = new long[3];
        
        if(A.length <= 1)
        {
            return A[0];
        }
        if(A.length <= 2)
        {
            if(A[0] > A[1])
            {
                return A[0];
            }
            else
            {
                return A[1];
            }
        }
        
        //因为用的求余算法，这样先完整算出前三个值，然后再替换
        result[0] = A[0];
        result[1] = Math.max(A[0], A[1]);
        result[2] = Math.max(result[1], result[0] + A[2]);
        for(int i = 3; i < A.length; i++)
        {
            result[i % 3] = result[(i - 2) % 3] + A[i]; //每次去找往前两个的值
            if(result[i % 3] < result[(i - 1) % 3]) //如果要是比邻近值小的话，那就用临近值，保证是当前值是最大价值；和上面是一个道理，就是用求余实现的
            {
                result[i % 3] = result[(i - 1) % 3];
            }
        }
        return result[(A.length - 1) % 3]; //返回最后一个值即可
    }
}
