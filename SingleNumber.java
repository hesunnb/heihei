/*Given an array of integers, every element appears twice except for one. Find that single one.

Example
Given [1,2,2,1,3,4,3], return 4

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?*/


/*
For anyone who didn't understood why this works here is an explanation. 
This XOR operation works because it's like XORing all the numbers by itself. 
So if the array is {2,1,4,5,2,4,1} then it will be like we are performing this operation

    ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5.

Hence picking the odd one out ( 5 in this case).
*/


public class Solution {
    public int singleNumber(int[] nums) {
        
        //数组肯定是奇数个, 用位操作都能找出来
        
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
             result ^= nums[i];
        }
        
        return result;
    }
    /*异或规律总结:
    a ^ a = 0
    a ^ 0 = a
    a ^ b = c => b ^ c = a => c ^ a = b
    a ^ b = b ^ a
    (a ^ b) ^ c = c ^ (b ^ a)
    */
}
