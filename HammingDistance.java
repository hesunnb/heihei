/*The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.*/


class Solution {
    //solution1:(own), 也可以把x^y先异或, 再循环查里面的1
    public int hammingDistance(int x, int y) {
        
        int sum = 0;
        for(int i = 0; i < 32; i++) {
            if((x & 1) != (y & 1)) {
                sum++;
            }
            x >>>= 1;
            y >>>= 1;
        }
        return sum;
    }
    
    
    //solution2:
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y); //bitCount是查传进去的int转成2进制数后有多少个1, ^是异或, 不同为真, 相同为假
    }
}
