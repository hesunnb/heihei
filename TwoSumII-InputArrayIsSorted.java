/*Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target 
number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution and you may not use the same element twice.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2*/

class Solution {
    
    //因为已经是顺序了, 所以双指针头尾相加缩小范围就行啦, 不用哈希表了
    public int[] twoSum(int[] numbers, int target) {
        
        int[] result = new int[2];
        
        if (numbers == null || numbers.length < 2) {
            return result;
        }
        
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }
}
