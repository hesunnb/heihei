/*Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:

The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.
The first ten numbers in Fibonacci sequence is:

0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

 Notice

The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.

Example
Given 1, return 0

Given 2, return 1

Given 10, return 34*/

class Solution {
    /**
     * @param n: an integer
     * @return an integer f(n)
     */
    
    //leetcode: leetcode和lintcode这道题定义有区别在于开头, leetcode是F(0) = 0, lintcode是F(1) = 0
    public int fib(int N) {
        
        int a = 0, b = 1, sum = 0;
        if(N == 0) {
            return a;
        }
        if(N == 1) {
            return b;
        }
        for(int i = 0; i < N - 1; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }
 
    //非递归, lintcode
    public int fibonacci(int n) {
        // write your code here
       int a = 0, b = 1, sum = 0;
       if(n <= 1) {
           return a;
       }
       if(n == 2) {
           return b;
       }
       for(int i = 0; i < n - 2; i++) {
           sum = a + b;
           a = b;
           b = sum;
       }
       return sum;
    }
    
    
    //递归, 递归复杂度: O(2^n)
    public int fibonacci(int n) {
        
        if(n <= 1) {
            return 0;
        }
        if(n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2); //一个一个拆解，到最小合回来
    }
}
