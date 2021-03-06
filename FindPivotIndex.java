/*Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the 
right of the index.

If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

Example 1:
Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
Example 2:
Input: 
nums = [1, 2, 3]
Output: -1
Explanation: 
There is no index that satisfies the conditions in the problem statement.
Note:

The length of nums will be in the range [0, 10000].
Each element nums[i] will be an integer in the range [-1000, 1000].*/

class Solution {
    
    //solution1:更易理解与写
    public int pivotIndex(int[] nums) {
        int sum = 0, left = 0;
        for (int num : nums) {
            sum += num;
        }
        
        for (int i = 0; i < nums.length; i++) { //对于2,0,0,0   0,0,0,2都适用
            if (left * 2 == sum - nums[i]) {
                return i;
            }
            left += nums[i];
        }

        return -1;  
    }
    
    //solution2:想法和上面一样, 写法不同
    //testcase: 0,0,0,0,0    1,0,0,0,0这种的返回的index是0, 也就是说0下标的左边和就是0; 同理0,0,0,0,1这种返回下标4
    public int pivotIndex(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        int sum = 0, left = 0;
        for(int i : nums) {
            sum += i;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(i != 0) {
                left += nums[i - 1]; //left一直都保持着i左边的和, 所以只是一遍, O(n)
            }
            if(sum - nums[i] - left == left) { //sum每次要把当前值减掉, 然后再减left的值
                return i;
            }
        }
        return -1;
    }
}
