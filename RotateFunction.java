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

    //solution1: 数学公式推导法
    public int maxRotateFunction(int[] A) {
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int allSum = 0; //数组元素的和
        int len = A.length;
        int F = 0;
        for (int i = 0; i < len; i++) {
            F += i * A[i]; //F最开始的值就是F(0)了
            allSum += A[i];
        }
        int max = F;
        for (int i = len - 1; i >= 1; i--) {
            F = F + allSum - len * A[i]; //算其他的F()
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
    
    
    //solution2: (own)
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
