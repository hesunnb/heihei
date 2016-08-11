/*
Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.
Notice

The subarray should contain at least one number

Example

For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], 
they both have the largest sum 7.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
     
    //利用了Maximum Subarray第一问程序
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        if(nums == null || nums.size() == 0) {
            return -1;
        }
        
        int[] left = new int[nums.size()]; //开一个正序存储maxSoFar的数组
        int[] right = new int[nums.size()]; //开一个倒序存储maxSoFar的数组
        int maxEndHere = nums.get(0); 
        int maxSoFar = left[0] = nums.get(0);
        for(int i = 1; i < nums.size(); i++) { //正序
            maxEndHere = Math.max(maxEndHere + nums.get(i), nums.get(i));
            maxSoFar = Math.max(maxEndHere, maxSoFar);
            left[i] = maxSoFar; //把maxSoFar存入
        }
        
        maxEndHere = nums.get(nums.size() - 1);
        maxSoFar = right[nums.size() - 1] = nums.get(nums.size() - 1);
        for(int i = nums.size() - 2; i >= 0; i--) { //倒序
            maxEndHere = Math.max(maxEndHere + nums.get(i), nums.get(i));
            maxSoFar = Math.max(maxEndHere, maxSoFar);
            right[i] = maxSoFar; //把maxSoFar存入
        }
        
        maxSoFar = Integer.MIN_VALUE; //记住一定回归最小值
        for(int i = 0; i < nums.size() - 1; i++){ //这个就是正序1个maxSoFar+倒序其它的所有数的maxSoFar, 正序前两个maxSoFar+倒序其它的所有数的maxSoFar...这些的和进行比较, 总会有一个正序maxSoFar+倒序maxSoFar的和是最大的, 找到返回即可
            maxSoFar = Math.max(maxSoFar, left[i] + right[i + 1]);
        }
        return maxSoFar;
    }
}

