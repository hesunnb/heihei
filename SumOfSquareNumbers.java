/*Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False*/

class Solution {

    //solution1: time complexity of it is only O(sqrt(n)), 因为就是left到right(right的值就是sqrt(n))每个值都访问了一遍
    public boolean judgeSquareSum(int c) {
        
        //为什么right--之后left不用从0开始从新匹配新的right: 因为平方是绝对放大, 3*3,4*4,不仅底数变大, 而且放大倍数也变大, 所以3*3与4*4之间的差值
        //一定比100*100与101*101的小, 所以就造成right--之后与刚刚的那个left相加一定会小于c, 也就不会出现right连续相减两次的情况
        //再举个例子: 1,4,9,16,25,36,49,64,81 比如c是91, 9+81=90小于91, 那么9就会跳到16(增加了差值7), 这时16+81=97比91大, 所以right--, 
        //但是之前说了, 后面的平方数之间的差值一定比前面要大, 所以81到64减小了17, 减小的大于增加的, 所以减小之后的right与之前增加的left的和肯定还是
        //相对是小的, 所以right不会连续减两次, left也不用每次都要从0开始
        if (c < 0) {
            return false;
        }
        
        int left = 0, right = (int)Math.sqrt(c);
        while (left <= right) {
            int cur = left * left + right * right; //题目中说只需要两个整数相加, 多个平方数相加的不算
            if (cur < c) {
                left++;
            } else if (cur > c) {
                right--;
            } else {
                return true; //背不住有多组两个平方数相加等于c的, 但是只要找到一组就可以了, 直接返回true
            }
        }
        return false;
    }
}
