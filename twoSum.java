/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.*/

public class Solution {

    //version1: O(n)time, O(n)space
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if(nums == null || nums.length == 0) {
            return result;
        }
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            if(m.containsKey(target - nums[i])) {
                result[0] = m.get(target - nums[i]); //哈希表中一定是小的那个
                result[1] = i; //刚刚扫描到的一定是大的那个
            } else {
                m.put(nums[i], i);
            }
        }
        return result;
    }
    
    
    //version2: O(n^2)time, O(1)space
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
}
