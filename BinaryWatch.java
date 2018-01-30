/*A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could 
represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".*/


class Solution {
    public List<String> readBinaryWatch(int num) {
        if(num < 0) {
            return null;
        }
        
        List<String> result = new ArrayList<String>();
        for(int h = 0; h < 12; h++) {
            for(int m = 0; m < 60; m++) {
                //if(Integer.bitCount(h) + Integer.bitCount(m) == num) { //两种解法
                if(Integer.bitCount((h << 6) + m) == num) {
                    result.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return result;
    }
    //因为正好表中的数1,2,4,8...化成二进制都只有一个1, 所以bitCount有1了就是选中了这个数, 也正好说明了这个二进制表的意义
    /*Take n=2 to understand this.
    1:01 is one valid time.
    so h=1 m=1
    Integer.BitCount(h+m)= Integer.BitCount(1+1)= 1 // number of 1s in 2
    Integer.BitCount(h*64+m)= Integer.BitCount(65)= 2 // number of 1s in 65
    If you don’t understand the above two statements, follow along.
    Why multiply by 64 or left shift of 6?
    To understand this, first address the following questions.
    What is the maximum value of m can be? The answer is 59 (check the condition in for loop).
    How many bits are required to represent 59? 6 bits.
    Represent m=1 in bits.
     m=                                  0  0   0  0   1  
    Now add hours (h=1) to m. If you do h+m, let see what will happen (1+1=10)
             h =                        0 0 0 0 0 1
           m=                           0 0 0 0 0 1 
           h+m=                       0 0  0  0 1 0
    what is number of 1’s in h+m now? 1
    if h=1 and m=1, there are two LEDs are glowing. But h+m says only one glowing. It is not capturing the idea properly. 
    Our situation is to add hours without distorting minutes bit. So left shift h to 6. h<<6. If you shift lesser than 6, you will 
    distort m bits. You could go beyond six shifts, but it is unnecessary.
                h    =  1 0 0 0 0 0
               m    =   0 0 0 0 0 1 
               h+m=   1 0  0  0 0 1                                   
    What is the bit count now? 2
    This equals to the number of LEDs growing.
    In short, you could do this. Integer.bitCount(h)+Integer.bitCount(m).*/
}
