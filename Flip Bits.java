/*Determine the number of bits required to flip if you want to convert integer n to integer m.
Both n and m are 32-bit integers.

Example

Given n = 31 (11111), m = 14 (01110), return 2.

*/

class Solution {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int result = 0;
        int tempa = 0;
        int tempb = 0;
        for(int i = 0; i < Integer.SIZE; i++) //Integer.SIZE, int的位数
        {
            tempa = a & 1; //1前面全是0, 所以只能得到1位
            tempb = b & 1;
            if(tempa != tempb)
            {
                result++;
            }
            a = a >>> 1;
            b = b >>> 1;
        }
        return result;
    }
};
