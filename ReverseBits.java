/*Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
	    for (int i = 0; i < 32; i++) {
	        result += n & 1;
	        n >>>= 1;   // CATCH: must do unsigned shift, >>>逻辑右移补0, >>算数右移补1
	        if (i < 31) // CATCH: for last digit, don't shift! i是0的时候, result移位完就已经是2位了, 所以i在30的时候, 
          //result就是32位
	            result <<= 1;
	    }
	    return result;
    }
}
