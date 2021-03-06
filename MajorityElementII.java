/*Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]

Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

Example 3:

Input: [1,1,3,3,3,2,2]
Output: [3]

注意这回是要找出所有大于n/3的元素
*/

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        
        List<Integer> result = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < nums.length; i++) {
            if(candidate1 == nums[i]) { //和其中某一个相等就++
                count1++;
            } else if(candidate2 == nums[i]) {
                count2++;
            } else if(count1 == 0) { //说明candidate现在所存储的值已经完全被消去
                candidate1 = nums[i]; //重新更换新的值
                count1 = 1; //设置新的值出现的次数为1
            } else if(count2 == 0) {
                candidate2 = nums[i];
                count2 = 1;
            } else { //都不等就--
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for(int i = 0; i < nums.length; i++) { //因为要找的值超过了1/3, 那么每三个不同的数消除, 最后肯定会把那个值挑出来, 
        //但是需要在candidate1与candidate2里面再挑一次, 因为两个数只有其中一个是, 另一个是其余的结果
            if(candidate1 == nums[i]) {
                count1++;
            } else if(candidate2 == nums[i]) {
                count2++;
            }
        }
        
        //按照题意7个数里面能超过3的最多有两个数, 最少可以没有, 8也是, 9个数的话就要每个数出现4次才算超过1/3, 所以最多就有两个数
        //所以两个candidate就够了, 最后要分别判断count1和count2的原因就是超过1/3的数可能有一个, 也可能没有, 题目没有说一定存在两个
        if(count1 > (nums.length / 3)) { // 7 / 3 = 2, 8 / 3 = 2, 大于两次的才要, 而 9 / 3 = 3, 那就是大于3, 4次的才要了, 
        //所以不用转成double计算
            result.add(candidate1);
        }
        if(count2 > (nums.length / 3)) {
            result.add(candidate2);
        }
        return result;
    }
}
