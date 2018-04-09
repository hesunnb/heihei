/*Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.
Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.
Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.*/

class Solution {
    
    //solution1:
    public boolean hasAlternatingBits(int n) {
        
        //0和1都算真
        if(n < 0) {
            return false;
        }
        /*We know that if we shift the number by 1 to the right, all the ones will become zeros and vice versa. Now if we AND those 
        two numbers, we can get the whole thing as zero but that won’t work for numbers like 2, 4, 8… So we will take another approach. 
        Instead of AND we will do an XOR . This will make all bits 1. Now we need to check if all the bits are 1. The best way to do 
        that is AND the number by (number+1) . It’ll give you zero.*/
        /*
        n =         1 0 1 0 1 0 1 0
        n >> 1      0 1 0 1 0 1 0 1
        n ^ n>>1    1 1 1 1 1 1 1 1
        n           1 1 1 1 1 1 1 1
        n + 1     1 0 0 0 0 0 0 0 0
        n & (n+1)   0 0 0 0 0 0 0 0
        */

        n = n ^ (n>>>1);
        return (n & (n+1)) == 0;
    }
    
    //solution2:
    public boolean hasAlternatingBits(int n) {
        
        //0和1都算真
        if(n < 0) {
            return false;
        }
        /*Xor the number with itself shifted right twice and check whether everything after the leading 1-bit became/stayed 0. 
        Xor is 0 iff the bits are equal, so we get 0-bits iff the pair of leading 1-bit and the 0-bit in front of it are repeated 
        until the end.

        000101010
      ^ 000001010
      = 000100000*/
        return ((n ^= n>>>2) & (n-1)) == 0; //方法思路同solution1, 一个+1法, 一个-1法
    }
    
    //solution3:
    public boolean hasAlternatingBits(int n) {
        
        //0和1都算真
        if(n < 0) {
            return false;
        }
        
        String s = Integer.toBinaryString(n);
        char temp = s.charAt(0);
        for(int i = 1; i < s.length(); i++) {
            if(temp != s.charAt(i)) {
                temp = s.charAt(i);
            } else {
                return false;
            }
        }
        return true;
    }
}
