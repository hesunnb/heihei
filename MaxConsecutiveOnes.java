/*Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000*/

class Solution {

    //自己一遍搞出, easy
    public int findMaxConsecutiveOnes(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int sum = 0, max = 0;
        for(int step : nums) {
            if(step == 1) {
                sum += step;
                max = Math.max(max, sum);
            } else {
                sum = 0;
            }
        }
        return max;
    }
}
