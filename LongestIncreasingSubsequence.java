/*Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, 
it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?*/

public class Solution {
    
    //时间O(nlogn)解法
    public int lengthOfLIS(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] count = new int[nums.length + 1]; //count长度比nums大1
        count[0] = Integer.MIN_VALUE; //让第一个值是最小值
        for(int i = 1; i <= nums.length; i++) { //其余的值都是最大值
            count[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 0; i < nums.length; i++) { //遍历nums, 对每一个数进行二分查找
            // find the first number in count > nums[i]
            int index = binarySearch(count, nums[i]);
            count[index] = nums[i];
        }
        
        for(int i = count.length - 1; i >= 0; i--) { //从尾往回找, 遇到第一个不是Integer.MAX_VALUE的就是要找的i
            if(count[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return 0;
    }
    
    // find the first number in count > val
    private int binarySearch(int[] count, int val) {
        int start = 0;
        int end = count.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(count[mid] < val) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if(count[start] > val) {
            return start;
        }
        return end;
    }
    
    /*
    testCase:
    nums  4 2 4 5 3 7 1  
    count 4 m m m m m m  
          2 4 5 m m m m //每次都相当于新来一个值, 然后找到count中第一个比它大的值然后替换掉
          2 3 5 7 m m m
          1 3 5 7 m m m
    */
}


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
