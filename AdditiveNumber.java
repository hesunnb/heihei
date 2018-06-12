/*Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the 
sequence 
must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:

Input: "112358"
Output: true 
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true 
Explanation: The additive sequence is: 1, 99, 100, 199. 
             1 + 99 = 100, 99 + 100 = 199
Follow up:
How would you handle overflow for very large input integers?: 用BigInteger*/

import java.math.BigInteger;

class Solution {
    
    /*The idea is quite straight forward. Generate the first and second of the sequence, check if the rest of the string match the sum
    recursively. i and j are length of the first and second number. i should in the range of [0, n/2]. The length of their sum should 
    >=max(i,j)*/
	
    //solution2: 用递归
    public boolean isAdditiveNumber(String num) {
	
	if(num == null || num.length() == 0) {
            return false;
        }
	
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) { //i和j分别是第一个和第二个数的长度
            if (num.charAt(0) == '0' && i > 1) return false; //"011235"这种例子就是真, 当i的长度为1的时候, 0作为单独一个数计算; 当i的长度为
            //2的时候, 之后的所有长度的数都是以0开头, 就是有leading zero, 那么就全都是假了
            BigInteger x1 = new BigInteger(num.substring(0, i));
            for (int j = 1; Math.max(j, i) <= n - i - j; j++) { //i和j的长度都比第三个数的长度小的时候
                if (num.charAt(i) == '0' && j > 1) break; //num.charAt(i)是第二个数的开头, 如果开头这个数是0并且长度(j)还大于1, 那么就跳
                //过计算
                BigInteger x2 = new BigInteger(num.substring(i, i + j));
                if (isValid(x1, x2, j + i, num)) return true;
            }
        }
        return false;
    }
    
    private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {
        if (start == num.length()) return true; //当start加到最后, 如果和num.length()相等就说明完全符合, 那么就结束了
        x2 = x2.add(x1); //x2变为相加结果
        x1 = x2.subtract(x1); //x1变为x2原来的数
        String sum = x2.toString();
        return num.startsWith(sum, start) && isValid(x1, x2, start + sum.length(), num); //startWith用法如下, 这句话就是如果第一段相加
        //结果满足就递归进行下一段, 继续相加判断
        /*String s = "199100199";
	System.out.println(s.startsWith("100", 3)); //startWith是判断对于s这个字符串从下标3开始"100"是不是"100199"这个子串的prefix
	System.out.println(s.startsWith("100", 2)); //从下标2开始是"9100199", 包含"100", 但是"100"并不是"9100199"的前缀, 所以假
		
	String ss = "199";
	System.out.println(ss.startsWith("199", 0)); //true*/
    }
	

    //solution3: 用迭代法, 也要用到BigInteger, 引入java.math.BigInteger;
    public boolean isAdditiveNumber(String num) {
        
        if(num == null || num.length() == 0) {
            return false;
        }
        
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) return false; //跟上面一样, 如果第一个数以0开头, 长度还比1大, 直接返回假
            for (int j = 1; Math.max(j, i) <= n - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) break; //如果第二个数以0开头, 长度还比1大, 直接break, 后面的数就不看了
                if (isValid(i, j, num)) return true;
            }
        }
        return false;
    }
    
    private boolean isValid(int i, int j, String num) {
        
        String sum;
        BigInteger x1 = new BigInteger(num.substring(0, i));
        BigInteger x2 = new BigInteger(num.substring(i, i + j));
        for (int start = i + j; start < num.length(); start += sum.length()) {
            x2 = x2.add(x1);
            x1 = x2.subtract(x1);
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
}
