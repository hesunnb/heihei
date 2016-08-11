/*Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 Example

For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
*/

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        int min[] = new int[nums.length];
        int max[] = new int[nums.length];
        max[0] = min[0] = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            max[i] = min[i] = nums[i];
            //正数乘正数变得更大, 负数乘正数变得更小
            if(nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]); //计算到当前i这步为止最大的乘积
                min[i] = Math.min(min[i], min[i - 1] * nums[i]); //计算到当前i这步为止最小的乘积
            } else if(nums[i] < 0) {
                //最大数乘负数变成最小数, 最小数乘负数变成最大数(无论正负都有效), 这个从数轴上看更明显
                //比如5,1同乘-1, -5,-1同乘-1
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }
            
            result = Math.max(result, max[i]);
        }
        return result;
    }
    
    /*
    testCase:
            1, 0, -1, 2, 3, -5, -2
    max     1  0   0  2  6  30  60
    min     1  0  -1 -2 -6  -30 -60
    result  1  1   1  2  6  30  60
    */
}
