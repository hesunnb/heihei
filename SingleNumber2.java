/*Given an array of integers, every element appears three times except for one. Find that single one.*/

//最开始让a, b都是0, 宗旨就是相同的数循环3次后, 还会复原成最开始的0, 0

public class Solution {
    public int singleNumber(int[] nums) {
        
        int a = 0;
        int b = 0;
        for(int i = 0; i < nums.length; i++) {
            a = (a ^ nums[i]) & ~b;
            b = (b ^ nums[i]) & ~a;
        }
        
        return a;
    }
}
