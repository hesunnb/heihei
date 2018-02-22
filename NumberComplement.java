/*Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.*/


class Solution {

    //solution1:位运算
    public int findComplement(int num) {
        
        //注意, 题目中说是正数了, positive integer, 下面这些方法对负数不适用
        return ~num & ((Integer.highestOneBit(num) << 1) - 1);
        
        //也可以不用左移1位, 因为比如00001011, Integer.highestOneBit之后就是00001000, 直接减一00000111, 然后按位与11110100
        //因为Integer.highestOneBit取到的最高位在~取反过程中一定会变为0, 所以不用特意为了这位留一位1用来与
        return ~num & (Integer.highestOneBit(num) - 1); //~包括符号位也会取反, 对于8位就满的值(计算器8位), 那么01111111就是最大值127, 
        //10000000是-128, -1变为正的127, 所以没有问题
        
        /*As several people pointed out, we don’t need to left shift 1. That’s true because the highest 1 bit will always become 0 in 
        the Complement result. So we don’t need to take care of that bit.*/
    }
    
    
    //solution2:
    public int findComplement(int num) {
        
        int sum = 0;
        int count = 0;
        
        while (sum < num) {
            sum += Math.pow(2, count); //i+Math.pow(2, j)的时候i转成double进行运算, 把结果重新赋给i的时候又转成int
            count++;
        }
        
        return sum - num;
    }
    /*for example:
    100110, its complement is 011001, the sum is 111111. So we only need get the min number large or equal to num, then do 
    substraction*/
}
