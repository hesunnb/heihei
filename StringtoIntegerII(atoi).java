/*
Implement function atoi to convert a string to an integer.

If no valid conversion could be performed, a zero value is returned.

If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
Have you met this question in a real interview?
Example

"10" => 10
"-1" => -1
"123123123123123" => 2147483647
"1.0" => 1
*/

public class Solution {
    /**
     * @param str: A string
     * @return An integer
     */
     
    //时间复杂度O(n)
    public int atoi(String str) {
        // write your code here
        
        if(str == null || str.length() == 0) {
            return 0;
        }
        
        str = str.trim(); //从第一个非空格字符开始
        if(str.length() == 0) {
            return 0;
        }
        
        long num = 0;
        int index = 0;
        int sign = 1; //正号
        if(str.charAt(index) == '+') { //正负号只能出现一个, 如果没有正负号，则默认为正
            index++;
        } else if(str.charAt(index) == '-') {
            index++;
            sign = -1; //负号
        }
        
        for(;index < str.length(); index++) {
            if(str.charAt(index) < '0' || str.charAt(index) > '9') { //遇到无效字符就退出, 比如" 15+4"这个例子中途遇到一个+号就退出了, 返回15
                break;
            }
            num = num * 10 + (str.charAt(index) - '0'); 
            if(num > Integer.MAX_VALUE) { //如果出现数值越界，则返回最接近的整数
                break;
            }
        }
        
        if(num * sign >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if(num * sign <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)num * sign;
    }
    
    /*一些testCase:
    String str = " 15+4";
    String str = "    -5211314";
    String str = "    k";
    */

    /*Requirements for atoi:
    The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
    Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
    and interprets them as a numerical value.

    The string can contain additional characters after those that form the integral number, which are ignored and have no effect on 
    the behavior of this function.

    If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because 
    either str is empty or it contains only whitespace characters, no conversion is performed.

    If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable 
    values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.*/
}
