/*A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.

Note:

Your solution should be in logarithmic complexity.
*/

class Solution {

    public int findPeak(int[] A) {
        // write your code here
        //这个数组它自己就必须满足要有一个峰
        //这道题只让找一个峰即可，所以二分收缩到任一一个峰返回就行
        //分四种情况：
        
        //1. >> 升升
        //2. << 降降
        //3. >< 降升
        //4. <> 升降
        
        //就看二分切到什么地方
        //复杂度O(logn):
        int start = 0, end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(A[mid] < A[mid - 1]) {
                end = mid; //说明左边有峰值，end向左窜
            }
            else if(A[mid] < A[mid + 1]) {
                start = mid; //说明右边有峰值, start向右窜
            }
            else {
                end = mid; //本身就是峰值，即不小于左边也不小于右边,而把mid给了end也就是end是峰值，但是看下面
            }
        }
        
        if(A[start] < A[end]) { //出来的话峰值一定属于start或者end其中的一个, 其实只return end就行，但是leetcode给出[2,1]这种，
            //２也算峰值，所以这里的start就起作用了，这种情况就返回了start
            return end;
        }
        else {
            return start;
        }
    }
    
    
    //O(n)
    public int findPeakElement(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        if(nums.length == 1) { //长度为1
            return 0;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if((i == 0 && nums[i] > nums[i + 1]) || (i == nums.length - 1 && nums[i] > nums[i - 1])) {
                return i; //长度为>=2, 比如[3,2,1], 3最大返回下标0
            }
            
            if(i > 0 && i < nums.length - 1 && nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i; //长度为>=3
            }
        }
        
        return -1;
    }
}
