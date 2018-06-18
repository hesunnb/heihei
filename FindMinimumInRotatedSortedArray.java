/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0*/

class Solution {

    //利用search in rotated sorted array的红绿线方法
    //复杂度：O(logn)
    public int findMin(int[] nums) {
    
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int start = 0, end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[start] < nums[mid] && nums[start] > nums[end]) { //保证start一定是红线的开头且mid在红线上的情况
                start = mid; //start向右窜
            }
            else {
                if(nums[mid] < nums[end]) { //绿线上的情况
                    end = mid; //小于的情况下，让end不断左窜
                }
            }
        }
        
        if(nums[start] < nums[end]) { //start,end定有一个是最小值，哪个小返回哪个
            return nums[start];
        } else {
            return nums[end];
        }
    }
}
