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
    
    //SplitArrayIntoFibonacciSequence的模板解法
    public boolean isAdditiveNumber(String S) {
        if(S == null || S.length() == 0) {
            return false;
        }
        
        return helper(S, new ArrayList<>(), 0);
    }
    
    public boolean helper(String S, List<Long> result, int index) {
        if(index == S.length() && result.size() >= 3) { //因为Fibonacci数列有效添加进入的数字可以多于3个, 所以要>=3
            return true;
        }
        for(int i = index; i < S.length(); i++) {
            if(S.charAt(index) == '0' && i > index) { //如果是以0开头, 那么就只能切出'0', 0以后的就不能切了
                break;
            }
            if(i + 1 - index > S.length() / 2) { //防止截取的字符串比Long的最大值还大
                return false;
            }
            long num = Long.parseLong(S.substring(index, i + 1));
            if(result.size() >= 2 && num > result.get(result.size() - 1) + result.get(result.size() - 2)) { //此处一定要用 num > 不能
                break; //用num !=, 比如"123456579", [123,456,579], 当走到123, 456, 子串切到5的时候, 5不大于123+456, 所以还会继续循环
            } //切出57, 最后到579满足条件; 如果要是用!=, 那么5!=123+456, 那么直接break了, 579这个有效数字就没有切到, 就错了
            if(result.size() < 2 || num == result.get(result.size() - 1) + result.get(result.size() - 2)) {
                result.add(num);
                if(helper(S, result, i + 1)) {
                    return true;
                }
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
	
	
    /*The idea is quite straight forward. Generate the first and second of the sequence, check if the rest of the string match the sum
    recursively. i and j are length of the first and second number. i should in the range of [0, n/2]. The length of their sum should 
    >=max(i,j)*/
	
    //solution1: 用long, 没有overflow的时候用long就可以, overflow用BigInteger
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
        long x1 = Long.parseLong(num.substring(0, i));
        long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start < num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = String.valueOf(x2);
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }
	
	
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
