/*Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.*/

public class Solution {
    public int trailingZeroes(int n) {
        
        int sum = 0;
        
        while(n > 0) {
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

