/*
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

    All elements < k are moved to the left
    All elements >= k are moved to the right

Return the partitioning index, i.e the first index i nums[i] >= k.
Notice

You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

If all elements in nums are smaller than k, then return nums.length

 Example

If nums = [3,2,2,1] and k=2, a valid answer is 1.
*/

public class Solution {
     
    //就是快排里面的partition部分
    public int partitionArray(int[] nums, int k) {
	    //write your code here
	    
	    if(nums == null || nums.length == 0) {
	        return 0;
	    }
	    
	    int store = 0;
	    for(int i = 0; i < nums.length; i++) {
	        if(nums[i] < k) { //store和i中间卡的都是大于等于k的
	            swap(nums, store, i);
	            store++;
	        }
	    } //在这里不用再交换一次的原因是我以一个固定数为轴, 不是数组的最后一个数, 而原来是int pivot = a[end];
	    //如果传nums.length就会越界
	    return store;
    }
    
    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
    //testcase: 2,1,5,6,2,3
}
