/*Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, 
where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]*/


class Solution {
    public int minMoves(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int minNum = Integer.MAX_VALUE;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < minNum) {
                minNum = nums[i];
            }
            sum += nums[i];
        }
        return sum - minNum * nums.length;
    }
}

/*解释:
let’s define sum as the sum of all the numbers, before any moves; minNum as the min number int the list; n is the length 
of the list;

After, say m moves, we get all the numbers as x , and we will get the following equation

 sum + m * (n - 1) = x * n
and actually,

  x = minNum + m
and finally, we will get

  sum - minNum * n = m
So, it is clear and easy now.

why x = minNum + m: before all elements reach to the same value, every time (n-1) elements add one meaning only one 
element remains the same, which of cause should be the max value( should be different from min value, otherwise they 
have reached the same value) of the array. So, with that being said, every time doing add one for (n-1) operation, 
the min value +1. If it takes m moves to reach x, then x=minNum+m.*/
