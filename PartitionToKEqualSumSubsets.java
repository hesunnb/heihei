/*Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets 
whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.*/

class Solution {
    
    //Partition Equal Subset Sum, Matchsticks to Square类型题, NP常规暴力搜索
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return false;
        }
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        
        Arrays.sort(nums);
        return dfs(nums, new int[k], nums.length - 1, sum / k, k);
    }
    
    private boolean dfs(int[] nums, int[] sums, int index, int target, int k) {
    	if (index == -1) { 
            return true; 
    	}
    	
    	for (int i = 0; i < k; i++) {
    	    if (sums[i] + nums[index] > target) {
                continue;
            }
    	    sums[i] += nums[index];
            if (dfs(nums, sums, index - 1, target, k)) {
                return true;
            }
    	    sums[i] -= nums[index];
    	}
    	
    	return false;
    }
}
