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
	/** 
     *@param nums: The integer array you should partition
     *@param k: As description
     *return: The index after partition
     */
     
    //就是快排里面的partition部分
    public int partitionArray(int[] nums, int k) {
	    //write your code here
	    
	    if(nums == null || nums.length == 0) {
	        return 0;
	    }
	    
	    int store = 0;
	    for(int i = 0; i < nums.length; i++) {
	        if(nums[i] < k) {
	            swap(nums, store, i);
	            store++;
	        }
	    }
	    return store;
    }
    
    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
}
