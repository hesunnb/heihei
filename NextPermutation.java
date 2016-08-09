/*
Given a list of integers, which denote a permutation.

Find the next permutation in ascending order.
Notice

The list may contains duplicate integers.

Example

For [1,3,2,3], the next permutation is [1,3,3,2]

For [4,3,2,1], the next permutation is [1,2,3,4]
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: return nothing (void), do not return anything, modify nums in-place instead
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        
        if(nums == null || nums.length == 0) {
            return nums;
        }
        
        // find the last increase index
        int index = -1;
        for(int i = nums.length - 2; i >= 0; i--) { //找到最后一个正序的第一个数, 说明就要在它后面开始换了
            if(nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        
        if(index == -1) { //比如4,3,2,1的时候i就会到-1, 说明这个是最大情况, 那就从头到尾reverse就可以了
            reverse(nums, 0, nums.length - 1);
            return nums;
        }
        
        // find the first bigger one
        int biggerIndex = index + 1;
        for(int i = nums.length - 1; i > index; i--) { //倒着找到第一个比index大的数, index与biggerIndex之间包含的都是大于等于index处值的数, 比如1,4,3,2,3,3,3,2; 1,4,3,2,3,3,3,3
            if(nums[i] > nums[index]) {
                biggerIndex = i;
                break;
            }
            //其他的例子还有1,4,3,2,3,9,8,2
        }
        
        // swap them to make the permutation bigger
        swap(nums, index, biggerIndex); //swap先让数变大
        // reverse the last part
        reverse(nums, index + 1, nums.length - 1); //swap之后index后面都是都是降序, reverse一次就是紧挨着的下一个了
        return nums;
    }
    
    private void reverse(int[] nums, int start, int end) {
        for(int i = start, j = end; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
