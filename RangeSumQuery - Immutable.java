/*Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.*/

class NumArray {

    //Java simple O(n) init and O(1) query solution, 这题重点就是o(n)的初始化, o(1)的查询, 普通做法没意思的
    int[] nums;
    public NumArray(int[] nums) {
        if(nums == null) { //构造函数也可以返回, 就是不接着往下初始化了
            return;
        }
        for(int i = 1; i < nums.length; i++) { //nums[i]就是从下标0到下标i的所有元素的和
            nums[i] += nums[i - 1];
        }
        
        this.nums = nums;
    }
    
    public int sumRange(int i, int j) {
        if(nums == null || i < 0 || i > j || j >= nums.length) { //在这里以0为无效值
            return 0;
        }
        
        if(i == 0) { //如果从头开始, 那么直接就是包括下标j之前所有的和
            return nums[j];
        }
        return nums[j] - nums[i - 1]; //比如从2到5, 就用nums[5]-nums[1]就行了
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
