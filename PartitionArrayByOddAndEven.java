/*Partition an integers array into odd number first and even number second.
Example

Given [1, 2, 3, 4], return [1, 3, 2, 4]
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        // write your code here;
        
        if(nums == null || nums.length == 0) {
            return;
        }
        
        int start = 0; int end = nums.length - 1;
        while(start < end) {
            while(start < end && nums[start] % 2 != 0) { //从前往后找偶数
                start++;
            }
            while(start < end && nums[end] % 2 == 0) { //从后往前找奇数
                end--;
            }
            
            if(start < end) { //加这个if的好处就是第一是安全, 第二就是start和end相等的那次就不用再交换一次值了
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
