/*Given an integer array nums with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

 Notice

The different sequences are counted as different combinations.

Example
Given nums = [1, 2, 4], target = 4

The possible combination ways are:
[1, 1, 1, 1]
[1, 1, 2]
[1, 2, 1]
[2, 1, 1]
[2, 2]
[4]
return 6
*/

public class Solution {
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
     
    /*给了目标包大小和各种包值的数组, 问能装多大体积或多大价值: 先把值放好(外层循环正序), 挨个走包(内层循环倒序)*/
    /*给了目标包大小和各种包值的数组, 问有多少种装法: 先把包放好(外曾循环正序), 挨个走值(内层循环正序):*/
    //只用一个一维数组
    public int backPackVI(int[] nums, int target) {
        // Write your code here
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] count = new int[target + 1];
        count[0] = 1;
        for(int i = 1; i <= target; i++) { //遍历包
            for(int j = 0; j < nums.length; j++) { //遍历值
                if(i >= nums[j]) { //j就是包数, nums[j]是每次走的值, j能容纳下所走的值的时候
                    count[i] += count[i - nums[j]]; //这个解释见下面注释
                }
            }
        }
        return count[count.length - 1];
    }
    
    /*
      0 1 2 3 4 //是一位数组, 只不过这样写出来方便看
    1 1 1 2 3 6 //当i是2的时候, 2 - 1 = 1, 就是包数为1的所有情况加上1; 2 - 2 = 0, 就是包数为0的所有情况加上2
    2           //当i是3的时候, 3 - 1 = 2, 就是包数位2的所有情况加上1; 3 - 2 = 1, 就是包数为1的所有情况加上2
    4           //4以此类推
    */
}
