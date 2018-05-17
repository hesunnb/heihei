/*Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.*/

class Solution {
    
    //solution1: 动态规划
    public int numSquares(int n) {
        
        if(n <= 0) {
            return 0;
        }
        
        // cntPerfectSquares[i] = the least number of perfect square numbers 
        // which sum to i. Note that cntPerfectSquares[0] is 0.
        int[] cntPerfectSquares = new int[n + 1];
        Arrays.fill(cntPerfectSquares, Integer.MAX_VALUE);
        cntPerfectSquares[0] = 0;
        
        for (int i = 1; i <= n; i++) { //动态规划是每一次新的计算都要用到前面的结果, 所以第一个循环是把1到12全部作为输入, 输入是1是什么结果,
            //计算输入2的时候用到1的结果, 计算输入3的时候用到1,2的结果
            // For each i, it must be the sum of some number (i - j*j) and 
            // a perfect square number (j*j).
            for (int j = 1; j*j <= i; j++) { //当输入是12的时候, 如果选1*1作为新加入的perfect square, 那么输入为11的结果就能用上了, 再加上1;
                //如果选2*2作为新加入的perfect square, 那么输入为8的结果就能用上了
                //如果选3*3作为新加入的perfect square, 那么输入为3的结果就能用上了
                cntPerfectSquares[i] = Math.min(cntPerfectSquares[i], cntPerfectSquares[i - j*j] + 1);
            }
        }
        
        return cntPerfectSquares[n];
    }
    
    
    //solution2: 数学方法
    // Based on Lagrange's Four Square theorem, there 
    // are only 4 possible results: 1, 2, 3, 4.
    public int numSquares(int n) {  
        // If n is a perfect square, return 1.
        if(isSquare(n)) {
            return 1;  
        }
        
        // The result is 4 if and only if n can be written in the 
        // form of 4^k*(8*m + 7). Please refer to 
        // Legendre's three-square theorem.
        while ((n & 3) == 0) { // n%4 == 0  
            n >>= 2;  
        }
        if ((n & 7) == 7) { // n%8 == 7
            return 4;
        }
        
        // Check whether 2 is the result.
        int sqrtN = (int)(Math.sqrt(n)); 
        for(int i = 1; i <= sqrtN; i++) {  
            if (isSquare(n - i*i))  {
                return 2;  
            }
        }  
        
        return 3;  
    }  
	
	private boolean isSquare(int n) {
        int sqrtN = (int)(Math.sqrt(n));  
        return (sqrtN*sqrtN == n);  
    }
}
