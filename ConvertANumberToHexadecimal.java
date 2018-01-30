/*Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; 
otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"*/

class Solution {
    public String toHex(int num) {
        
        if(num == 0) {
            return "0";
        }
        
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String result = "";
        while(num != 0) {
            result = map[num & 15] + result; //顺序不能反, 不能写成+=, 高位是要加在前面的, 15的二进制是1111, &15是取出4位用的
            num >>>= 4; //num逻辑右移4位
        }
        return result;
    }
    /*
    Basic idea: each time we take a look at the last four digits of
                binary verion of the input, and maps that to a hex char
                shift the input to the right by 4 bits, do it again
                until input becomes 0.

    */
}
