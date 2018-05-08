/*Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole 
array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.*/

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        
        /*I use the variables beg and end to keep track of minimum subarray A[beg...end] which must be sorted for the entire array A to 
        be sorted. If end < beg < 0 at the end of the for loop, then the array is already fully sorted.*/
        //testcase想一个: [2,3,5,6,4,8,10,9,15]
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length, beg = -1, end = -2, min = nums[n-1], max = nums[0];
        for (int i = 1; i < n; i++) { //testcase对于[1],[2,1]这种都管用
            max = Math.max(max, nums[i]); //max不断向后找比自己大的, 就是在搜正序列, 遇到降序列就标记一个end, 那么遇到的最后的降序列就是最后一个
            //end
            min = Math.min(min, nums[n-1-i]); //同理, min不断向前找降序列, 遇到升序列就标记一个begin, 那么遇到的最后的升序列就是最后一个begin
            if (nums[i] < max) end = i;
            if (nums[n-1-i] > min) beg = n-1-i; 
        }
        return end - beg + 1;
    }
}
