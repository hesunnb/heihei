/*Given two 32-bit numbers, N and M, and two bit positions, i and j. 
Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)

 Notice

In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Clarification
You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， 
you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, 
because M could not fully fit between bit 3 and bit 2.

Example
Given N=(10000000000)2, M=(10101)2, i=2, j=6

return N=(10001010100)2*/

class Solution {
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        // write your code here
        
        if(i < 0 || j < 0) {
            return 0;
        }
        
        int max = ~0; //max是32个1
        if(j == 31) {
            j = max; //如果j是31, 1 << (31 + 1)就越界了
        } else {
            j = (1 << (j + 1)) - 1; //让j位置后面都是1
        }
        
        int left = max - j; //让j左边都是1, 右边都是0
        int right = (1 << i) - 1; //让i左边都是0, 右边都是1
        
        int mask = left | right; //让i与j中间都是0, 其余位置都是1
        
        return (mask & n) | (m << i); //mask & n把n中i到j的位置都清0, n的其余位置都保留,
        //然后把m移位i放到i与j中间替换那些0
    }
}
