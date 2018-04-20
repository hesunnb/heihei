/*Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) 
which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].*/

class Solution {
    
    //solution1: 排序法
    public int arrayPairSum(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        int result = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i += 2) {
            //result += Math.min(nums[i], nums[i + 1]); //因为已经排好序了, 所以不用再用Math.min了
            result += nums[i];
        }
        return result;
    }
    
    
    //solution2: 这个解法只适用于本题, 思想通用, 但是解法不通用
    public int arrayPairSum(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        int[] exist = new int[20001]; //这个exist的长度是按照nums数组里面元素的范围确定的, 就是-10000到10000是20001个数
        //思想就是说把nums里面的元素当成exist数组的下标
        
		for (int i = 0; i < nums.length; i++) {
			exist[nums[i] + 10000]++; //+10000的原因是把-10000的值能够转成下标0, 其余的值最大会到20001下标处
		}
		int sum = 0;
		boolean odd = true;
		for (int i = 0; i < exist.length; i++) { //排好了之后等价于给所有元素排了个序
			while (exist[i] > 0) { //大于0的都是值为1的下标
				if (odd) { //只加奇数位置的, 等于Math.min操作了
					sum += i - 10000; //i是下标, 下标-10000就变成了nums里面对应的值
				}
				odd = !odd;
				exist[i]--; //加过的值--变为0, 下回while就会把它跳过去
			}
		}
		return sum;
    }
}
