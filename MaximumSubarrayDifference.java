/*
Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.

Return the largest difference.
Notice

The subarray should contain at least one number

 Example

For [1, 2, -3, 1], return 6.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two
     *          Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        int[] left = new int[nums.length]; //开一个正序存储maxSoFar的数组
        int[] right = new int[nums.length]; //开一个倒序存储maxSoFar的数组
        int[] leftMin = new int[nums.length]; //开一个正序存储minSoFar的数组
        int[] rightMin = new int[nums.length]; //开一个倒序存储minSoFar的数组
        
        int maxEndHere = nums[0]; 
        int maxSoFar = left[0] = nums[0];
        for(int i = 1; i < nums.length; i++) { //正序
            maxEndHere = Math.max(maxEndHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxEndHere, maxSoFar);
            left[i] = maxSoFar; //把maxSoFar存入
        }
        
        int minSoFar = nums[0];
        int minEndHere = leftMin[0] = nums[0];
        for(int i = 1; i < nums.length; i++) { //正序
            minEndHere = Math.min(minEndHere + nums[i], nums[i]);
            minSoFar = Math.min(minSoFar, minEndHere);
            leftMin[i] = minSoFar; //把minSoFar存入
        }
    
        maxEndHere = nums[nums.length - 1];
        maxSoFar = right[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--) { //倒序
            maxEndHere = Math.max(maxEndHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxEndHere, maxSoFar);
            right[i] = maxSoFar; //把maxSoFar存入
        }
        
        minEndHere = nums[nums.length - 1];
        minSoFar = rightMin[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i >= 0; i--) { //倒序
            minEndHere = Math.min(minEndHere + nums[i], nums[i]);
            minSoFar = Math.min(minEndHere, minSoFar);
            rightMin[i] = minSoFar; //把minSoFar存入
        }
        
        //用正序的最大值减去倒序的最小值
        int result = Integer.MIN_VALUE; //记住一定回归最小值
        for(int i = 0; i < nums.length - 1; i++){ 
            result = Math.max(result, Math.abs(left[i] - rightMin[i + 1]));
        }
        //用倒序的最大值减去正序的最小值
        for(int i = nums.length - 1; i > 0; i--){ 
            result = Math.max(result, Math.abs(right[i] - leftMin[i - 1]));
        }
        //一定走两遍, 然后综合得出最后的最大值
        return result;
    }
}

