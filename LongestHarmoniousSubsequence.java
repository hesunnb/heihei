/*We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].
Note: The length of the input array will not exceed 20,000.*/

class Solution {
    public int findLHS(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1); //把每一个数出现的次数都统计到哈希表里
        }
        
        for(int key : map.keySet()) {
            if(map.containsKey(key + 1)) { //加1或者减1用一个就行
                result = Math.max(result, map.get(key) + map.get(key + 1)); //算出相差1距离的两个数的和, 取最大值
            }
        }
        return result;
    }
}
