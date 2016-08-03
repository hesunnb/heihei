/*After robbing those houses on that street, the thief has found himself a new place for his thievery so that 
he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same 
as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money 
you can rob tonight without alerting the police.

Notice

This is an extension of House Robber.

Example

nums = [3,6,4], return 6
*/

public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        // write your code here
        if (nums.length == 1){
            return nums[0];
        }
        
        int last1 = 0;
        int now1 = 0;
        int last2 = 0;
        int now2 = 0;
        
        //Not including the last house, 从头到最后一个的前一个的最大
        for(int i = 0; i < nums.length - 1; i++) { 
            int temp = now1;
            now1 = Math.max(last1 + nums[i], now1); 
            last1 = temp;
        }
        
        //Not including the first house, 从最后一个到第一个的后一个最大
        for(int i = nums.length - 1; i > 0; i--) { 
            int temp = now2;
            now2 = Math.max(last2 + nums[i], now2); 
            last2 = temp;
        }
        return Math.max(now1, now2); //返回两个中最大的
    }
}
