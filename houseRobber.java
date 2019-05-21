/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Example

Given [3, 8, 4], return 8.
*/

public class Solution {
    
    //状态方程: dp[i] = max(dp[i-1], dp[i-2] + A[i-1])
    public int rob(int[] nums) {

        if(nums == null || nums.length == 0) {
            return 0;
        }
        int last = 0; //last是now的前一个最大
        int now = 0;
        for(int i = 0; i < nums.length; i++) { //对于头两个元素来说, 最开始last是头0大, now变成了头1大; 接着last是头1大, 
        //now变成头2大, 往下以此类推
            int temp = now;
            now = Math.max(last + nums[i], now); //假设第三个数来, 就是头1大加上第三个数与头2大做比较成为头3大给now, 
            //last变成头2大...
            last = temp;
        }
        return now;
    }
}
