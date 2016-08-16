/*Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, 
it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?*/

public class Solution {
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
     
    //时间复杂度O(n^2)解法
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] count = new int[nums.length];
        int max = 1; //输入为1个元素的情况下默认最大子序列就是自己1
        
        for(int i = 0; i < nums.length; i++) {
            count[i] = 1; //count每次新值的时候都会初始化为1, 表示自己的一次
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) { //i处前面的值比i处的小
                    count[i] = count[i] > count[j] + 1 ? count[i] : count[j] + 1; //更新i处的值, i处值表示截止到i的最长子序列
                }
                max = Math.max(max, count[i]); //用max记录最大, count[nums.length - 1]不是最大的, 见testcase
            }
        }
        return max;
        /*
        testcase:
        nums  4 2 4 5 3 7 1
        count 1 1 2 3 2 4 1  //两个数组的变化过程
        */
    }
}
