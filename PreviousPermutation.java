/*
Given a list of integers, which denote a permutation.

Find the previous permutation in ascending order.
Notice

The list may contains duplicate integers.

Example

For [1,3,2,3], the previous permutation is [1,2,3,3]

For [1,2,3,4], the previous permutation is [4,3,2,1]
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		// write your code
		
		if(nums == null || nums.size() == 0) {
		    return nums;
		}
		
		int index = -1;
		for(int i = nums.size() - 2; i >= 0; i--) {
		    if(nums.get(i) > nums.get(i + 1)) {
		        index = i;
		        break;
		    }
		}
		
		if(index == -1) {
		    reverse(nums, 0, nums.size() - 1);
		    return nums;
		}
		
		int smallerIndex = index + 1;
		for(int i = nums.size() - 1; i > index; i--) {
		    if(nums.get(i) < nums.get(index)) {
		        smallerIndex = i;
		        break;
		    }
		}
		
		swap(nums, index, smallerIndex);
		reverse(nums, index + 1, nums.size() - 1);
		return nums;
    }
    
    private void reverse(ArrayList<Integer> nums, int start, int end) {
        for(int i = start, j = end; i < j; i++, j--) {
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
    }
    
    private void swap(ArrayList<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
}
