/*Implement pow(x, n).

 Notice

You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is 
smaller than 1e-3.

Example
Pow(2.1, 3) = 9.261
Pow(0, 1) = 0
Pow(1, 0) = 1*/

public class Solution {
    //O(logn) time
    public double myPow(double x, int n) {
        if(n == 0) { //指数为0返回1
            return 1.0; 
        }
        if(x == 0) { //底数为0返回0
            return 0.0; 
        }
        
        if(n % 2 == 0) { //能被2整除底数翻倍, 指数减半
            return myPow(x * x, n / 2);
        } else { //不能被2整除就是算一个系数, 然后乘上整除的部分
            return (n > 0 ? x : 1.0 / x ) * myPow(x * x, n / 2) ;
        }
        //testcase:
        //2^8, 2^7, 2^-8, 2^-9
    }
}
