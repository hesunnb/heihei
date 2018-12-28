/*Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Note: Your solution should be in logarithmic time complexity.
*/

public class Solution {

    public int trailingZeroes(int n) {
        
        int sum = 0;
        while(n > 0) { //其实就是在查有多少个5, 因为尾部的0都来自于2*5, 2的个数永远比5多, 所以有多少个5就有多少个trailingzero
            n = n / 5;
            sum += n;
        }
        return sum;
    }
    
  
    // Discussion recursion
    public int trailingZeroes(int n) {
	if(n < 5) return 0;
	else return n/5 + trailingZeroes(n/5);
    }
}

