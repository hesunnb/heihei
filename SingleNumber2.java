/*Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3

Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99

*/

public class Solution {
	/**
	 * @param A : An integer array
	 * @return : An integer 
	 */
	 
    //容易理解, O(32n)的时间
    public int singleNumberII(int[] A) {
        // write your code here
        
        if(A == null || A.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        int result = 0; //如果落单的数是0, 那么result在后面就没有参与运算, 直接返回0
        
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < A.length; j++) { //让每个数右移0位, 1位...到31位
                if((A[j] >> i & 1) == 1) { //如果位置上为1, 也应该是3个, 不是3个就是那个多出来的数多那么一位
                    sum++;
                    sum %= 3;
                }
            }
            
            if(sum == 1) { //所以在这里就在result中把多出来那一位放到result里面; 题中说落单的数只出现1次, 不会有两次的情况出现
                result |= (sum << i); //sum只可能是1, 因为别人都是3个一起的, 只有一个数多余, 所以出来的不是0就只能是1
            }
        }
        return result;
    }
}


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
