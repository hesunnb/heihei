/*Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.
The algorithm should run in linear time and in O(1) space.

Notice

There is only one majority number in the array.

Example

Given [1, 2, 1, 2, 1, 3, 3], return 1.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
     
    //思路就是每三个不一样的就消除, 所以用两个candidate和新进来的第三个比较
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(candidate1 == nums.get(i)) { //和其中某一个相等就++
                count1++;
            } else if(candidate2 == nums.get(i)) {
                count2++;
            } else if(count1 == 0) { //说明candidate现在所存储的值已经完全被消去
                candidate1 = nums.get(i); //重新更换新的值
                count1 = 1; //设置新的值出现的次数为1
            } else if(count2 == 0) {
                candidate2 = nums.get(i);
                count2 = 1;
            } else { //都不等就--
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for(int i = 0; i < nums.size(); i++) { //因为要找的值超过了1/3, 那么每三个不同的数消除, 最后肯定会把那个值挑出来, 
        //但是需要在candidate1与candidate2里面再挑一次, 因为两个数只有其中一个是, 另一个是其余的结果
            if(candidate1 == nums.get(i)) {
                count1++;
            } else if(candidate2 == nums.get(i)) {
                count2++;
            }
        }
        return count1 > count2 ? candidate1 : candidate2;
    }
}
/*testCase:
1,2,1,2,1,3,3,4,4,1,5,5
*/
