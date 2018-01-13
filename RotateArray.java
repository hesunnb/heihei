/*Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.*/

class Solution {
    public void rotate(int[] nums, int k) {
        
        if(nums == null || nums.length == 0 || k <= 0) {
            return;
        }
        k %= nums.length; //k比数组长的情况
        if(k == 0) {
            return;
        }
        
        reverse(nums, 0, nums.length-k-1);
        reverse(nums, nums.length-k, nums.length-1);
        reverse(nums, 0, nums.length-1);
    }
    
    public void reverse(int[] nums, int start, int end) { //传过来的是地址, 集合和数组都可以直接交换, 值就不可以啦
    
        for(int i = start, j = end; i < j; i++, j--) { //翻转的具体实现
            int temp = nums[i]; //就是交换两个数
            nums[i] = nums[j]; //交换
            nums[j] = temp; //交换
        }
    }
}
