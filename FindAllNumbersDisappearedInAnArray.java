/*Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]*/


class Solution {
    
    //FindAllDuplicatesInAnArray类似题
    //标记法, 把nums中元素按照位置去理解, 把出现过的位置标记为负, 重复出现的位置不标记, 最后不是负数的位置就是没有出现过的元素
    public List<Integer> findDisappearedNumbers(int[] nums) {
        
        List<Integer> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1; //位置, abs不能缺, 计算位置的时候要用正数
            if(nums[val] > 0) { //这个位置的元素大于0, 重复元素会跳过(因为第一次已经置为负, 不会再大于0)
                nums[val] = -nums[val]; //置位负
            }
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
