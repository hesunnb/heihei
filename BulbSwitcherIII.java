/*There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. Initially, all the bulbs are turned off.

At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb change color to blue only if it is on and all the previous 
bulbs (to the left) are turned on too.

Return the number of moments in which all turned on bulbs are blue.

 

Example 1:



Input: light = [2,1,3,5,4]
Output: 3
Explanation: All bulbs turned on, are blue at the moment 1, 2 and 4.
Example 2:

Input: light = [3,2,4,1,5]
Output: 2
Explanation: All bulbs turned on, are blue at the moment 3, and 4 (index-0).
Example 3:

Input: light = [4,1,2,3]
Output: 1
Explanation: All bulbs turned on, are blue at the moment 3 (index-0).
Bulb 4th changes to blue at the moment 3.
Example 4:

Input: light = [2,1,4,3,6,5]
Output: 3
Example 5:

Input: light = [1,2,3,4,5,6]
Output: 6
 

Constraints:

n == light.length
1 <= n <= 5 * 10^4
light is a permutation of  [1, 2, ..., n]*/

class Solution {

    /*testcase: { 5, 2, 1, 4, 3 }题目的意思是所有的灯泡要一起闪才行, 不能一部分闪, 比如这个例子, 5打开了, 然后放2, 然后
    放1, 虽然2前面1亮了, 1,2满足条件, 但是5不满足(原因是已经亮的灯泡要全都能闪才叫闪), 所以最终解法是看max*/
    public int numTimesAllBlue(int[] light) {
        if(light == null || light.length == 0) {
            return 0;
        }
        
        int result = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i]);
            if(max == i + 1) { //max出现在了它该出现的位置上(max的值和位置值相等), 说明max前面的值都比max小, 并且前面的位置都已经被填满了
                result++;
            }
        }
        return result;
    }
}
