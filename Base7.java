/*Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].*/

class Solution {
    public String convertToBase7(int num) {
        
        StringBuilder result = new StringBuilder();
        int step = num;
        if(step == 0) {
            return "0";
        }
        
        while(step != 0) {
            result.append(Math.abs(step % 7)); //针对负数的情况, 每次都要abs, 然后在最后添上负号
            step /= 7;
        }
        
        if(num < 0) {
            return result.append("-").reverse().toString(); //负数添个负号
        }
        return result.reverse().toString();
    }
}
