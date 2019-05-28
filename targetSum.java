/*You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, 
you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.*/

class Solution {
    
    //最简单暴力dfs, Just do DFS and try both "+" and "-" at every position
    int result = 0;
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        helper(nums, S, 0, 0);
        return result;
    }
    
    public void helper(int[] nums, int S, int pos, int curSum) {
        if(pos == nums.length) {
            if(curSum == S) {
                result++;
            }
            return;
        }
        
        helper(nums, S, pos + 1, curSum + nums[pos]);
        helper(nums, S, pos + 1, curSum - nums[pos]);
    }
    
    
    //记忆化搜索, dfs前准备一个后n项数组和, 用来提前结束无效递归
    
}
