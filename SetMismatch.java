/*The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got 
duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and 
then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.*/


class Solution {
    public int[] findErrorNums(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        Arrays.sort(nums);
        
        int sumPre = (1 + nums.length) * nums.length / 2;
        int sumAfter = 0;
        int duplicateNum = 0;
        int[] result = new int[2];
        
        for(int i = 0; i < nums.length; i++) {
            if(i + 1 < nums.length && nums[i] == nums[i+1]) {
                duplicateNum = nums[i]; //找到重复的数
            }
            sumAfter += nums[i];
        }
        
        int diff = Math.abs(sumPre - sumAfter); //比如像[2,2]这种会返回[2,1], 所以要区分sumPre与sumAfter的大小
        if(sumPre > sumAfter) {
            result[0] = duplicateNum; //题目给顺序了, result[0]必须是重复的那个数
            result[1] = duplicateNum + diff; //result[1]是缺失的那个数
        } else {
            result[0] = duplicateNum;
            result[1] = duplicateNum - diff;
        }
        
        return result;
    }
}
