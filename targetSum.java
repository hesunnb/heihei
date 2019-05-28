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
    int result = 0;
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int[] sums = new int[nums.length];
        sums[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--) { //这里就是求一个后n项和
            sums[i] = sums[i + 1] + nums[i];
        }
        helper(nums, sums, S, 0, 0);
        return result;
    }
    
    public void helper(int[] nums, int[] sums, int S, int pos, int curSum) {
        if(pos == nums.length) {
            if(curSum == S) {
                result++;
            }
            return;
        }
        
        if(Math.abs(S - curSum) <= sums[pos]) { //这里的意思就是, 如果S和curSum的差值要是大于sums[pos], 即从pos开始的后n项和, 就没有必要递归下去
            //了, 比如[1,2,3,4,5] target=3举例, sums是[15,14,12,9,5], 1+2+3+4=10了, abs(3-10)=7, 7大于5, 说明后面的n项和无论是+5, 还是-5, 
            //都无法弥补这个差值7, 也就是10+5, 10-5都无法囊括这个target值3, 那么向下递归就不可能curSum == S, 所以就提前结束递归
            helper(nums, sums, S, pos + 1, curSum + nums[pos]);
            helper(nums, sums, S, pos + 1, curSum - nums[pos]);
        }
    }
}
