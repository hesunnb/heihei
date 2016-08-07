/*Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.*/

/*数组不能为空, 且总有次数最多的元素, 重点是超过[n / 2]次, 这就保证了major总是能够换到1上*/

/*
testCase:
1, -1, 1, -1, 1 //交替

-1, 1, -1, 1, 1 //交替

-1, -1, 1, 1, 1 //连续

1, 1, -1, -1, -1 //连续
*/

public class Solution {
    //思路就是每两个不一样的就消除, 最后那个大于一半的数肯定会留下来
    public int majorityElement(int[] num) {

        if(nums == null || nums.length == 0) {
            return 0;
        }

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){ //count = 0的位置说明之前的正好全部抵消完, 从这个位置重新计
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;

        }
        return major;
    }
}
