/*Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]*/

class Solution {
    
    //FindAllNumbersDisappearedInAnArray类似题
    public List<Integer> findDuplicates(int[] nums) {
        
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1; //位置, abs不能缺, 计算位置的时候要用正数
            if(nums[val] > 0) { //这个位置的元素大于0, 重复元素到else(因为第一次已经置为负, 不会再大于0)
                nums[val] = -nums[val]; //置位负
            } else { //因为不会有0出现, 所以直接else
                result.add(val + 1); //已经为负的就是重复值
            }
        }
        
        return result;
    }
}
