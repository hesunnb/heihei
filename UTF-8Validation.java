/*A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer 
represents only 1 byte of data. 
//这句话的意思就是: Integer都是32位的, 那么这32位中只有最低的8位在这里起作用, 所以一个Integer就代表了一个字节
//的数据; 所以最终的解法就按照上面那个图来, 遍历一遍满足这4种格式就行

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001. //两个UTF-8的字符, 197,130组成了第一个, 1自己是第二个

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.*/

class Solution {
   
    //solution1: (own), O(n)+位运算, 遍历一遍, 按照上面4种格式进行check
    public boolean validUtf8(int[] data) {
        
        if(data == null || data.length == 0) {
            return false;
        }
        
        int sum = 0;
        int base = 0x80;
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < 8; j++) {
                if((data[i] & base) == base) { //查有多少个1
                    sum++;
                    base >>>= 1;
                } else {
                    break;
                }
            }
            
            if(sum == 0) { //如果是0xxxxxxx, 直接返回, 因为肯定是有效的
                continue;
            } else {
                if(sum < 2 || sum > 4 || data.length < i+sum) return false; //如果查出来的1的个数为1个或者5,6...太多了, 
                //或者后面的10不够多, 那么也返回假
                int check = 0xC0;
                for(int k = i+1; k < i+sum; k++) { //检查是否有足够并且正好多的10
                    if((data[k] & check) != 0x80) {
                        return false;
                    }
                }
            }
            i += sum-1; //i向后窜
            sum = 0; //sum与base要回复原值
            base = 0x80;
        }
        return true;
    }
}
