/*Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.

*/

public class Solution {
    
    //如果按照顺序从1开始用x除, 一直除到平方根是根号n的复杂度, n的任意次方都比log(n)增长的速度要快, 实质比的是根号n与logn的速度
    public int mySqrt(int x) {
        if(x == 0) {
            return 0;
        }
        
        int start = 1, end = x;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid <= x / mid) { //如果写成mid * mid <= x, 对于给long的情况就会越界, 所以改为除法(仅限一次)
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end <= x / end) { //这里也要改为除法, 比如start = 46340, end = 46341的时候, 46341的平方会大于Integer.MAX_VALUE, 所以也要用除法写
            return end; //先判断end的原因是, 比如start与end同时小于平方根, 如果先返回start就错了(因为end更接近与平方根)
        }
        return start;
    }
}
