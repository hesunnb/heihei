/*Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum 
of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.*/

class Solution {

    //Partition to K Equal Sum Subsets, Matchsticks to Square类型题
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        
        Arrays.sort(nums);
        return dfs(nums, new int[2], nums.length - 1, sum / 2);
    }
    
    private boolean dfs(int[] nums, int[] sums, int index, int target) {
    	if (index == -1) { 
            return true; 
    	}
    	
    	for (int i = 0; i < 2; i++) {
    	    if (sums[i] + nums[index] > target) {
                continue;
            }
    	    sums[i] += nums[index];
            if (dfs(nums, sums, index - 1, target)) {
                return true;
            }
    	    sums[i] -= nums[index];
    	}
    	
    	return false;
    }
}
