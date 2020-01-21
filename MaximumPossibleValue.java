/*Maximum Possible Value
Write a function that given an integer N, returns the maximum possible value obtained by inserting one '5' digit inside the decimal 
representation of integer N.

Example
Example1:
Input: N = 268
Output: 5268
Example2:
Input: N = 670
Output: 6750
Example3:
Input: N = 0
Output: 50
Example4:
Input: N = -999
Output: -5999
Notice
N is an integer within the range [-8000, 8000]*/

public class Solution {
    /**
     * @param N: an integer
     * @return: returns the maximum possible value obtained by inserting one '5' digit
     */
    
    //testcase: 565 -> 5655; -524 -> -5245
    public int MaximumPossibleValue(int N) {
        // write your code here
        
        StringBuilder sb = new StringBuilder(String.valueOf(N));
        if(N >= 0) {
            int i = 0;
            for(i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) < '5') { //对于上面的testcase只要注意这里不能带等号就行, <=不行
                    sb.insert(i, '5');
                    break;
                }
            }
            if(i == sb.length()) {
                sb.append('5');
            }
        } else {
            int i = 1;
            for(i = 1; i < sb.length(); i++) {
                if(sb.charAt(i) > '5') { //不能带等号, >=不行
                    sb.insert(i, '5');
                    break;
                }
            }
            if(i == sb.length()) {
                sb.append('5');
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
