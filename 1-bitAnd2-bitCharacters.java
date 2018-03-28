/*We have two special characters. The first character can be represented by one bit 0. The second character can be represented by two 
bits (10 or 11).

Now given a string represented by several bits. Return whether the last character must be a one-bit character or not. The given string 
will always end with a zero.

Example 1:
Input: 
bits = [1, 0, 0]
Output: True
Explanation: 
The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
Example 2:
Input: 
bits = [1, 1, 1, 0]
Output: False
Explanation: 
The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
Note:

1 <= len(bits) <= 1000.
bits[i] is always 0 or 1.*/

class Solution {
    
    //题目的意思: 给定数组中仅有0和1两种元素, 且出现的元素组合只能有三种编码方式: 10,11,0, 判断给定的数组最后一个元素是否属于0编码, 而不是10编码
    public boolean isOneBitCharacter(int[] bits) {
        
        if(bits == null || bits.length == 0) {
            return false;
        }
        
        int i = 0;
        while(i < bits.length - 1) { //输入的bits一定要以0结尾, 所以[1]这种testcase是不行的, 所以长度为1的时候只能是[0], 然后返回真
            if(bits[i] == 0) {
                i++;
            }
            if(bits[i] == 1) {
                i+=2;
            }
        }
        if(i == bits.length - 1) {
            return true;
        }
        return false;
    }
    /*明白题目意图, 就会发现, 题目的意思是要判断最后一个0元素是属于0还是属于10
    遍历数组, 给定指针, 若当前位为1则指针+2; 若当前位为0, 则指针+1
    判断最后指针是否与bits.length-1相等, 相等则为真, 否则为假; 其中length=1的情况也包括进去了.*/
}
