/*A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and 
negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a 
wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, 
[1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its 
last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by 
deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original 
order.

Example 1:

Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.
Example 2:

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
Example 3:

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?*/

class Solution {
    
    //O(n)的贪心很机智啊
    public int wiggleMaxLength(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int f = 1, b = 1; //the first number can be a smaller number or larger number depends on what the next number it is. 
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                f = b + 1;
            }
            else if (nums[i] < nums[i-1]) {
                b = f + 1;
            }
        }
        return Math.max(f, b);
    }
    /*1, 17, 5, 10, 13, 15, 10, 5, 16, 8
         f2  b3 f4  f4  f4  b5  b5 f6  b7
    f4就表明这些都是增序列, 都有可能, b5就是都是降序列, 也是都有可能选*/
}