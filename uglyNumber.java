/*
Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly 
since it includes another prime factor 7.

Notice

Note that 1 is typically treated as an ugly number.

Example

Given num = 8 return true
Given num = 14 return false
*/

public class Solution {
    /**
     * @param num an integer
     * @return true if num is an ugly number or false
     */
     
    //正常3个while
    public boolean isUgly(int num) {
        // Write your code here
        
        if(num <= 0) {
            return false;
        }
        while(num % 2 == 0) {
            num /= 2;
        }
        while(num % 3 == 0) {
            num /= 3;
        }
        while(num % 5 == 0) {
            num /= 5;
        }
        if(num == 1) {
            return true;
        }
        return false;
    }
    
    
    //tricky一个while
    public boolean isUgly(int num) {
        // Write your code here
        for (int i = 2; i < 6 && num > 0; i++) //多个对4求余, 4又等于2*2, 所以不影响, 因为前面已经对2求余求尽了, 所以论不到对4求余
          //假设对14求个余, 那么就会多个7, 含有因子7的就会通过, 所以不行, 从这里看, 4没有其它的因子, 只有2, 所以通过
          while (num % i == 0) {
              num /= i;
          }
        return num == 1;
    }
}
