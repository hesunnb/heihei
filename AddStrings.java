/*Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.*/

class Solution {

    //solution2:(own)
    public String addStrings(String num1, String num2) {
        if(num1 == null || num2 == null) {
            return null;
        }
        StringBuilder sb1 = new StringBuilder(num1);

        StringBuilder sb2 = new StringBuilder(num2);
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int remain = 0;
        int sum = 0;
        while(!sb1.toString().equals("") && !sb2.toString().equals("")) {
            sum = sb1.charAt(sb1.length() - 1) - '0' + sb2.charAt(sb2.length() - 1) - '0' + carry;
            carry = sum / 10;
            remain = sum % 10;
            result.insert(0, remain);
            sb1.replace(sb1.length() - 1, sb1.length(), "");
            sb2.replace(sb2.length() - 1, sb2.length(), "");
        }
        
        while(!sb1.toString().equals("")) {
            sum = sb1.charAt(sb1.length() - 1) - '0' + carry;
            carry = sum / 10;
            remain = sum % 10;
            result.insert(0, remain);
            sb1.replace(sb1.length() - 1, sb1.length(), "");
        }
        while(!sb2.toString().equals("")) {
            sum = sb2.charAt(sb2.length() - 1) - '0' + carry;
            carry = sum / 10;
            remain = sum % 10;
            result.insert(0, remain);
            sb2.replace(sb2.length() - 1, sb2.length(), "");
        }
        if(carry != 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }
}
