/* Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint. 
*/

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int i = 0;
        int j = i;
        int diff = Integer.MAX_VALUE;
        int sum = 0;
        while(i < nums.length) {
            while(j < nums.length) {
                sum += nums[j];
                if(sum >= s) {
                    diff = Math.min(diff, j - i + 1);
                    sum -= (nums[i] + nums[j]); //虽说是两个循环套在一起, 但是这样写的好处是, 每次计算完局部的和之后把i和j处的值去掉
                    //然后i会后窜一位, 而j还在原处, 下次会从那个位置接着计算, 所以O(n)一遍就扫下来了
                    break;
                } else {
                    j++;
                }
            }
            i++;
        }
        if(diff == Integer.MAX_VALUE) { //如果第一次j扫到头还没有s大的话, 那么j就会一直在nums的结尾, 之后i++的时候每次第二个j的循环
        //都进不去, 所以还是O(n)就结束了
            return 0;
        }
        return diff;
    }
}
