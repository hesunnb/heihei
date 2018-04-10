/*Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you 
need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].*/

class Solution {

    //solution2:
    public double findMaxAverage(int[] nums, int k) {
        
        if(nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return Double.NEGATIVE_INFINITY;
        }
        
        double result = Double.NEGATIVE_INFINITY;
        int sum = 0;
        
        for(int i = 0; i < k; i++) { //先求好一个和
            sum += nums[i];
        }
        
        for(int i = 0; i < nums.length - k + 1; i++) {
            double step = (double)sum / k;
            if(step > result) {
                result = step;
            }
            if(i + k < nums.length) { //用sum每次减一个头, 加一个尾, 这样就不用每次都重复加和k个数
                sum -= nums[i];
                sum += nums[i + k];
            }
        }
        return result;
    }
}
