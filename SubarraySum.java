/*Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number 
and the index of the last number.

 Notice

There is at least one subarray that it's sum equals to zero.

Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        
        int sum = 0;
        m.put(0, -1); //在-1的位置放置0, -1位置的前n项和为0, 对于和为0的子数组开头的情况(1, -1 ...)
        for(int i = 0; i < nums.length; i++) { //从头开始求和, 如果再后面遇到和与之前一样的, 说明中间夹了一个和为0的子数组, 
        //返回这个子数组的开头和结尾坐标即可
            sum += nums[i];
            if(m.containsKey(sum)) {
                result.add(m.get(sum) + 1); //找到子数组开头的位置
                result.add(i); //子数组结尾的位置
                return result; //如果要是返回所有的和为0的数组的下标, 去掉这句return result, 只在最后返回即可
            }
            m.put(sum, i);
        }
        return result;
    }
}
