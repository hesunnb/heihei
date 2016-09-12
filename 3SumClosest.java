/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers.

Notice

You may assume that each input would have exactly one solution.

Example

For example, given array S = [-1 2 1 -4], and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        
        if (numbers == null || numbers.length < 3) {
            return -1;
        }
        int minDiff = Integer.MAX_VALUE; //存最小差值
        int result = 0; //结果
        Arrays.sort(numbers); //一定要排个序
        for(int i = 0; i < numbers.length - 2; i++) { //3个一组
            if(i != 0 && numbers[i] == numbers[i - 1]) { // to skip duplicate numbers, 比如-4, -1, -1, -1, 0, 1, 2, 
            //第一个-1已经涵盖了所有可能的组合, 后面的-1只能是前面的重复或是子集
                continue;
            }
            
            int left = i + 1; //left在i的后一个
            int right = numbers.length - 1;
            while(left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                int diff = target - sum;
                if(Math.abs(diff) <= Math.abs(minDiff)) { //等于保证第一次嫩够进入, result就会更新为sum
                    minDiff = diff;
                    result = sum;
                }
                if(diff == 0) { //等于0的时候直接返回了, 跳过重复在下面这几个判断中都可以跳过, 不过这道题不用跳, 也费不了多少时间
                    return sum;
                } else if(sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
