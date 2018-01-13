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
