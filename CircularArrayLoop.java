/*You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. 
Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, 
and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a 
particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.

Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

Example 2: Given the array [-1, 2], there is no loop.

Note: The given array is guaranteed to contain no element "0".

Can you do it in O(n) time complexity and O(1) space complexity?*/

class Solution {
    
    /*[-2, 1, -1, -2, -2]这是一个无效的loop, For example, starting at index 1, nums[1] is 1, move 1 step forward to index 2. 
    Then nums[2] is -1, move back 1 step to index 1. The loop contains indices 1 and 2. Is this a valid loop?
    解释: The question said that it can be only backward loop or forward loop. 1 -> 2, 2 -> 1 is a forward - backward loop.*/
    
    public boolean circularArrayLoop(int[] nums) {
        
        if(nums == null || nums.length <= 1) { //[1,1]就是有效的loop
            return false;
        }
        
        
    }
}
