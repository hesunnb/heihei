/* Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5]. 

 Note:

    The order of the result is not important. So in the above example, [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/


/*
explanation:

In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. Note that since the 
two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. Find
out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, 
another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups. 
XOR numbers in each group, we can find a number in either group.
*/

public class Solution {
    public int[] singleNumber(int[] nums) {
        
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for(int i = 0; i < nums.length; i++) {
            diff ^= nums[i];
        }
        
        // Get its last set bit
        diff &= -diff; //3与5在set bit位是不相等的, 所以能够分到不同的两组, 其余的数按照set bit也会分成两组, 进入两组的数组随意,
        //因为都是两两进入, 所以没有问题
        /*计算机都是采用补码进行运算, Integer.toBinaryString()显示的都是补码; 比如6与-6按位与, 因为-6是取反+1, 取反按位与的结果一定是0, 
        但是取反之后又+1, 所以按位与的时候一定会留下一个1, 这个1也就是想要的set bit*/
        
        // Pass 2 :
        int[] result = {0,0}; // this array stores the two numbers we will return
        for(int i = 0; i < nums.length; i++) {
            if((diff & nums[i]) == 0) {
                result[0] ^= nums[i]; // the bit is not set
            } else {
                result[1] ^= nums[i]; // the bit is set
            }
        }
        
        return result;
    }
    /*
    比如6跟-6。。
    6的原码是0110， 补码一样是0110，取反则是1001，加1则是1010即是-6的补码。
    验证一下，-6的原码是1110， 反码是1001，加1是1010.
    从-6的补码取反是0101，加1是0110。即是6的补码
    */
}
