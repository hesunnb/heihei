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
    
    
    public boolean isUgly(int num) {
        // Write your code here
        for (int i = 2; i < 6 && num > 0; i++)
          while (num % i == 0) {
              num /= i;
          }
        return num == 1;
    }
}
