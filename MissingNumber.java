/*Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?*/

public class Solution {
    /**    
     * @param nums: an array of integers
     * @return: an integer
     */
    public int findMissing(int[] nums) {
        // write your code here
        int sum = 0;
        int total = 0;
        for(int i = 0; i < nums.length; i++)
        {
            sum += nums[i];
        }
        total = nums.length * (nums.length + 1) / 2; //对所有的n个数进行求和, 首项+末项nums.length, 长度nums.length + 1
        return total - sum;
    }
}
