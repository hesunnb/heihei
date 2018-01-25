/*Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False*/

class Solution {
    
    //solution1: 复杂度是O(sqrt(n)), n=1计算1次, n=4计算2次, n=9计算3次, n=16级计算4次, 每次都是计算根号n次
    public boolean isPerfectSquare(int num) {
        if(num <= 0) {
            return false;
        }
        
        int i = 1;
        while(num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
    /*This is a math problem：
    1 = 1
    4 = 1 + 3
    9 = 1 + 3 + 5
    16 = 1 + 3 + 5 + 7
    25 = 1 + 3 + 5 + 7 + 9
    36 = 1 + 3 + 5 + 7 + 9 + 11
    ....
    so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn*/
    
    
    
    //solution2: binary search time complexity is O(log(n))
    public boolean isPerfectSquare(int num) {
        if(num <= 0) {
            return false;
        }
        
        long low = 1, high = num; //low是0是1都可以
        while (low <= high) {
            long mid = (low + high) / 2; //因为num是positive number, 所以(low+high) >>> 1逻辑右移也可
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
