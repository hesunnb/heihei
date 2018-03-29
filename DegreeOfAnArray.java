/*Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its 
elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.*/

class Solution {
    
    //题目的意思是: 首先找出数组的度(就是出现最多次数的那个元素的次数), 然后找到一个subarray能够满足包含所有度的元素并且要最短, 其实正好一个子数组
    //刚好把这些度的元素包含了就正好了
    public int findShortestSubArray(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, int[]> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) { //扫一遍, 把度, 每个元素的起始位置, 最后一次的中止位置都弄出来了
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i, i});
                // the first element in array is degree, second is first index of this key, third is last index of this key
            } else {
                int[] temp = map.get(nums[i]); //地址, 直接改, 不用再put
                temp[0]++;
                temp[2] = i;
            }
        }
        
        int degree = Integer.MIN_VALUE; int result = Integer.MAX_VALUE;
        for(int[] value : map.values()) {
            if(value[0] > degree) {
                degree = value[0];
                result = value[2] - value[1] + 1;
            } else if(value[0] == degree) { //取短的那个subarray
                result = Math.min(result, value[2] - value[1] + 1);
            }
        }
        return result;
        
    }
}
