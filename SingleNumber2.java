/*Given an array of integers, every element appears three times except for one. Find that single one.*/

//最开始让a, b都是0, 宗旨就是相同的数循环3次后, 还会复原成最开始的0, 0
/*
testCase:
   i                    a0000 b0000
i = 0, A[i] = 5 = 0101, 0101, 0000
i = 1, A[i] = 5 = 0101, 0000, 0101
i = 2, A[i] = 5 = 0101, 0000, 0000
三次之后返回原样, 返回之后i的第一次操作就可以挑出那个独立的元素, 因为a一定等于那个元素
*/
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
