/*X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from 
X. Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each 
other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation: 
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Note:

N  will be in range [1, 10000].*/

class Solution {

    //自己写的把N转成String再判断包含至少一个2, 5, 6, 9, 不包含3, 4 or 7也能过, 就是慢了, 所以直接用求余
    //12旋转变成15也和原来的12不一样, 所以12有效; 像13这种就不行了, 3旋转之后什么也不是
    //traverse方法:
    public int rotatedDigits(int N) {
        if(N <= 0) {
            return 0;
        }
        
        int count = 0;
        for (int i = 1; i <= N; i ++) {
            if (isValid(i)) count ++;
        }
        return count;
    }
    public boolean isValid(int N) {
        /*
         Valid if N contains ATLEAST ONE 2, 5, 6, 9
         AND NO 3, 4 or 7s
         */
        boolean validFound = false;
        while (N > 0) {
            if (N % 10 == 2 || N % 10 == 5 || N % 10 == 6 || N % 10 == 9) {
                validFound = true;
            }
            if (N % 10 == 3 || N % 10 == 4 || N % 10 == 7) {
                return false;
            }
            N = N / 10;
        }
        return validFound;
    }
    
    
    //递归法:
    public int rotatedDigits(int N) {
        if(N <= 0) {
            return 0;
        }
        
        int count = 0;
        for (int i = 1; i <= N; i ++) {
            if (isValid(i, false)) count++;
        }
        return count;
    }
    
    public boolean isValid(int N, boolean validFound) {

        if (N % 10 == 3 || N % 10 == 4 || N % 10 == 7) {
            return false;
        }
        if (N % 10 == 2 || N % 10 == 5 || N % 10 == 6 || N % 10 == 9) {
            validFound = true;
        }
        
        N = N / 10;
        if(N > 0) {
            if(!isValid(N, validFound)) {
                return false; //如果有任何一位是假, 就一直返回false
            } else {
                validFound = true; //如果没有false, 这里需要改为真, 否则像20这样的数字, 第一位0并没有改变validFound, 所以如果
                //这里不改, 就会返回false了
            }
        }
        return validFound;
    }
}
