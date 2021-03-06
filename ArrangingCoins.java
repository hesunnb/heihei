/*You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.*/


class Solution {

    //solution1:求根公式法
    public int arrangeCoins(int n) {
        if(n <= 0) {
            return 0;
        }
        
        return (int) ((Math.sqrt(1 + 8.0 * n) - 1) / 2); //纯数学问题, Negative root is ignored and positive root is used instead. 
        //Note that 8.0 * n is very important because it will cause Java to implicitly autoboxed the intermediate result into double 
        //data type. The code will not work if it is simply 8 * n. Alternatively, an explicit casting can be done 8 * (long) n).
        
        /*1 + 2 + 3 + 4 + 5 + 6 + 7 + ... + x <= n
        (x * ( x + 1)) / 2 <= n
        然后就是x^2 + x - 2n <= 0
        不等式求根, 求根公式是x = (-b+-sqrt(b^2-4ac)) / 2
        因为是<=, 所以x1 <= x <= x2; 如果是>=, x <= x1 或 x >= x2
        所以这里取x2的值就是最终的值*/
    }
    
    
    /*solution2:二分法, 以n为结尾数进行二分
    1 + 2 + 3 + 4 + 5 + 6 + 7 + ... + x <= n
    (x * ( x + 1)) / 2 <= n
    Binary search is used in this case to slowly narrow down the `x` that will satisfy the equation. 
    Note that 0.5 * mid * mid + 0.5 * mid does not have overflow issue as the intermediate result is implicitly autoboxed to double 
    data type.*/
    public int arrangeCoins(int n) {
        if(n <= 0) {
            return 0;
        }
        
        int start = 0; 
        int end = n; 
        while (start + 1 < end) { 
            int mid = start + (end - start) / 2; 
            if ((0.5 * mid * mid + 0.5 * mid ) <= n) { //(1+mid)*mid/2的展开
                start = mid; 
            } else { 
                end = mid; 
            } 
        }
        
        if(0.5*start*start+0.5*start <= n && 0.5*end*end+0.5*end <= n) { //这个二分模板总能保证while出来的时候start<=end
            return end; //所以start计算与end计算同时都小于n的时候取最接近的
        }
        return start; //这种就是end计算后一定大于n, 所以返回start
    }
}
