/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example

Given [1,2,3] which represents 123, return [1,2,4].

Given [9,9,9] which represents 999, return [1,0,0,0].
*/

public class Solution {
    
    //写的这个方法不仅适用于plusOne, plus多少都可以
    public int[] plusOne(int[] digits) {
         
         if(digits == null || digits.length == 0) {
             return null;
         }

         int carry = 1; //把要加的那个1直接写在进位上
         for(int i = digits.length - 1; i >= 0; i--) {
             int temp = digits[i] + carry;
             digits[i] = temp % 10;
             carry = temp / 10;
         }
         
         if(carry == 1) {
             int[] result = new int[digits.length + 1]; //无论怎样都得开一个数组当最后一个有进位的时候
             for(int i = 0; i < digits.length; i++) { //digits拷贝到result
                 result[i + 1] = digits[i];
             }
             result[0] = carry; //把进位放到数组头
             return result;
         } 
         return digits;
    }
    
    
    // Nice small trick, 这个方法就只适用于plusOne了
    public int[] plusOne(int[] digits) {
        
        for (int i = digits.length - 1; i >=0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                break;
            } else {
                digits[i] = 0;
            }
        }
        
        if (digits[0] == 0) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            return res;
        }
        return digits;
    }
}
