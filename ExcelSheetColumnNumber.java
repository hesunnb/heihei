/*Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 */
    
class Solution {

//solution1:
    public int titleToNumber(String s) {
        
        if(s == null || s.length() == 0) {
            return -1;
        }
        
        int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result = result * 26 + (s.charAt(i) - 'A' + 1); 
            //相当于逢26进1, 跟逢10进1一个道理, 类比一下就明白了, 从高位向低位走, 乘上进位加相应位上的数
		}
		return result;
    }
    
    
    
//solution2:
    public int titleToNumber(String s) {
        
        if(s == null || s.length() == 0) {
            return -1;
        }
        
        int result = 0;
        for(int i = s.length(); i > 0; i--) {
        	result += (int)Math.pow(26, i - 1) * (s.charAt(s.length() - i) - 'A' + 1); 
            //每一位都乘以相应的26的个数, 然后再乘以相对A的差值
        }
        return result;
    }
}
