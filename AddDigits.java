/*Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?*/

class Solution {

    //solution2:(own)
    public int addDigits(int num) {
        
        if(num <= 0) {
            return 0;
        }
        int result = 0;
        while(num > 0) {
            result += num%10; //把结果都加到result中
            num /= 10;
            if(num == 0) {
                if(result < 10) {
                    return result; //结果小于10就返回
                }
                num = result; //值交换继续循环
                result = 0;
            }
        }
        return result;
    }
}
