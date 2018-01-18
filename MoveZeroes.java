/*Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.*/

class Solution {

    //solution1:(一个循环)
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        
        int cur = 0;

        for (int i = 0; i < nums.length; ++i) { //用的交换方式, cur永远会停在一个是0的地方, 然后遇到nums[i]不是0的时候交换
            if (nums[i] != 0) {
                int temp = nums[cur];
                nums[cur++] = nums[i];
                nums[i] = temp;
            }
        }
    }

    //solution2:(两个循环)
    public void moveZeroes(int[] nums) {
        
        // Shift non-zero values as far forward as possible
        // Fill remaining space with zeros
        if(nums == null || nums.length == 0) {
            return;
        }
        
        int insertPos = 0;
        for(int num : nums) { //遍历nums, 把非0值复制到数组的前面
            if(num != 0) {
                nums[insertPos++] = num;
            }
        }
        
        while(insertPos < nums.length) { //把剩余的部分清0
            nums[insertPos++] = 0;
        }
    }
}
