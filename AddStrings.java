/*Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.*/

class Solution {

    //solution1: 自己写的精简版
    public String addStrings(String num1, String num2) {
        if(num1 == null || num2 == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int x = 0, y = 0;
        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) { //即使num1或者num2是"", 也只会造成i,j小于0,
            //然后就+0, 没有影响的
            if(i < 0) {
                x = 0; //这样就算下标小于0也能加0而不影响
            } else {
                x = num1.charAt(i) - '0';
            }
            
            if(j < 0) {
                y = 0;
            } else {
                y = num2.charAt(j) - '0';
            }
           
            sb.insert(0, (x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        
        if(carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }
    
        
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
            sum = sb1.charAt(sb1.length() - 1) - '0' + sb2.charAt(sb2.length() - 1) - '0' + carry; //加差值加进位
            carry = sum / 10;
            remain = sum % 10;
            result.insert(0, remain);
            sb1.replace(sb1.length() - 1, sb1.length(), ""); //移除最后一位
            sb2.replace(sb2.length() - 1, sb2.length(), "");
        }
        
        while(!sb1.toString().equals("")) { //当sb1还剩余值
            sum = sb1.charAt(sb1.length() - 1) - '0' + carry;
            carry = sum / 10;
            remain = sum % 10;
            result.insert(0, remain);
            sb1.replace(sb1.length() - 1, sb1.length(), "");
        }
        while(!sb2.toString().equals("")) { //当sb2还剩余值
            sum = sb2.charAt(sb2.length() - 1) - '0' + carry;
            carry = sum / 10;
            remain = sum % 10;
            result.insert(0, remain);
            sb2.replace(sb2.length() - 1, sb2.length(), "");
        }
        if(carry != 0) { //把最后一位进位加上
            result.insert(0, carry);
        }
        return result.toString();
    }
}
