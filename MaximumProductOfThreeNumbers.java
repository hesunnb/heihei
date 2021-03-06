/*Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
Example 2:
Input: [1,2,3,4]
Output: 24
Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.*/

class Solution {
    
    //solution1:one pass
    public int maximumProduct(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        //是随意三个数, 不是连续的
        //Simply find out the three largest numbers and the two smallest numbers using one pass.
        //三个最大和两个最小, 组合就是三个最大的积or一个最大与两个最小的积
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }
    
    
    //solution2:排个序
    public int maximumProduct(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        Arrays.sort(nums);
        int a = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]; //3个最大
        int b = nums[0] * nums[1] * nums[nums.length - 1]; //1个最大, 2个最小
        return a > b ? a : b;
    }
}
