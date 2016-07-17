/*Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Solve it without division and in O(n).
For example, given [1,2,3,4], return [24,12,8,6].
Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)*/


//O(n)time, with extra space(two arrays, one order, one reverse)
public class Solution {
    public int[] productExceptSelf(int[] nums) { //求出一个正序数组, 求出一个倒序的数组, 然后把运算正序数组
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        int length = nums.length;
        int[] order = new int[length];
        int[] reverse = new int[length];
        int[] result = new int[length];
        
        order[0] = nums[0];
        for(int i = 1; i < length; i++) { //正序
            order[i] = order[i - 1] * nums[i];
        }
        
        reverse[length - 1] = nums[length - 1];
        for(int i = length - 2; i >= 0; i--) { //倒序
            reverse[i] = reverse[i + 1] * nums[i];
        }
        
        result[0] = reverse[1];
        result[length - 1] = order[length - 2];
        for(int i = 1; i < length - 1; i++) { //结果
            result[i] = order[i - 1] * reverse[i + 1];
        }
        
        return result;
    }
}



//O(n)time, O(1)space
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) { //从左乘到右, 没有乘自己
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) { //从右乘到左, right作为之前没有乘到的数
        res[i] *= right;
        right *= nums[i];
    }
    return res;
}
