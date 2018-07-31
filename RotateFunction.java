/*Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).

Note:
n is guaranteed to be less than 105.

Example:

A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.*/

class Solution {

    //solution1: 最易理解的方法O(n)搞定
    public int maxRotateFunction(int[] A) {
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int sum = 0, F = 0, len = A.length;
        
        for(int i = 0; i < len; i++) {
            sum += A[i];
            F += (A[i] * i);
        }
        
        int max = F;
        for(int j = 1; j < len; j++) {
            // for next iteration lets remove one entry value of each entry and the prev 0 * k
            F = F - sum + A[j-1]*len;
            max = Math.max(max, F);
        }
        
        return max;
    }
    /*Consider we have 5 coins A,B,C,D,E

    According to the problem statement
    F(0) = (0A) + (1B) + (2C) + (3D) + (4E)
    F(1) = (4A) + (0B) + (1C) + (2D) + (3E)
    F(2) = (3A) + (4B) + (0C) + (1D) + (2*E)

    This problem at a glance seem like a difficult problem. I am not very strong in mathematics, so this is how I visualize this problem

    We can construct F(1) from F(0) by two step:
    Step 1. taking away one count of each coin from F(0), this is done by subtracting "sum" from "iteration" in the code below
    after step 1 F(0) = (-1A) + (0B) + (1C) + (2D) + (3*E)

    Step 2. Add n times the element which didn't contributed in F(0), which is A. This is done by adding "A[j-1]len" in the code below.
    after step 2 F(0) = (4A) + (0B) + (1C) + (2D) + (3E)

    At this point F(0) can be considered as F(1) and F(2) to F(4) can be constructed by repeating the above steps.

    Hope this explanation helps, cheers!*/
    
    //solution2: 数学公式推导法
    public int maxRotateFunction(int[] A) {
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int sum = 0; //数组元素的和
        int len = A.length;
        int F = 0;
        for (int i = 0; i < len; i++) {
            F += i * A[i]; //F最开始的值就是F(0)了
            sum += A[i];
        }
        
        int max = F;
        for (int i = len - 1; i >= 1; i--) {
            F = F + sum - len * A[i]; //算其他的F()
            max = Math.max(F, max);
        }
        return max;
    }
    /*解析: F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]
    F(k-1) = 0 * Bk-1[0] + 1 * Bk-1[1] + ... + (n-1) * Bk-1[n-1]
           = 0 * Bk[1] + 1 * Bk[2] + ... + (n-2) * Bk[n-1] + (n-1) * Bk[0]
    Then,

    F(k) - F(k-1) = Bk[1] + Bk[2] + ... + Bk[n-1] + (1-n)Bk[0]
                  = (Bk[0] + ... + Bk[n-1]) - nBk[0]
                  = sum - nBk[0]
    Thus,

    F(k) = F(k-1) + sum - nBk[0]
    What is Bk[0]?

    k = 0; B[0] = A[0];
    k = 1; B[0] = A[len-1];
    k = 2; B[0] = A[len-2];
    ...*/
    
    
    //solution3: (own)
    public int maxRotateFunction(int[] A) {
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int sum = 0;
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++) { //O(n2)时间复杂度
            for(int j = 0; j < A.length; j++) {
                sum += j * A[j];
            }
            if(sum > result) result = sum;
            sum = 0;
            rotate(A, 1); //每次rotate一位, 不用开空间了
        }
        return result;
    }
    
    private void rotate(int[] nums, int k) {
   
        reverse(nums, 0, nums.length-k-1);
        reverse(nums, nums.length-k, nums.length-1);
        reverse(nums, 0, nums.length-1);
    }
    
    private void reverse(int[] nums, int start, int end) { //传过来的是地址, 集合和数组都可以直接交换, 值就不可以啦
    
        for(int i = start, j = end; i < j; i++, j--) { //翻转的具体实现
            int temp = nums[i]; //就是交换两个数
            nums[i] = nums[j]; //交换
            nums[j] = temp; //交换
        }
    }
}
