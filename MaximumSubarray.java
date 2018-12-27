/*Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

public class Solution {
    
    //greedy: O(n)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere); 
        }
        return maxSoFar;
    }
    
    //Prefix Sum: O(n)
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        return max;
    }
}


//D & C(divide and conquer)
public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0 , nums.length - 1);
    }
    
    private int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = start + (end - start) / 2;
        int left = helper(nums, start, mid);
        int right = helper(nums, mid + 1, end);
        int leftSub = nums[mid];
        int rightSub = nums[mid + 1];
        int temp = 0;
        for (int i = mid; i >= start; i--) {
            temp += nums[i];
            if (temp > leftSub) leftSub = temp;
        }
        temp = 0;
        for (int i = mid + 1; i < nums.length; i++) {
            temp += nums[i];
            if (temp > rightSub) rightSub = temp;
        }
        return Math.max(leftSub + rightSub, Math.max(left, right));
    }
}


//Minimum Subarray:
public class Solution {
    
    //greedy: O(n)
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        int minSoFar = nums.get(0);
        int minEndHere = nums.get(0);
        
        for(int i = 1; i < nums.size(); i++) {
            
            minEndHere = Math.min(minEndHere + nums.get(i), nums.get(i));
            minSoFar = Math.min(minSoFar, minEndHere);
        }
        
        return minSoFar;
    }
    
    
    //prefix: O(n)
    public int minSubArray(List<Integer> nums) {
        // write your code here
        
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE, sum = 0, maxSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            min = Math.min(min, sum - maxSum);
            maxSum = Math.max(maxSum, sum);
        }

        return min;
    }
}
