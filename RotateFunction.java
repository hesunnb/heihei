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
