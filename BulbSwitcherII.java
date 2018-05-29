/*There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations 
towards buttons, you need to return how many different kinds of status of the n lights could be.

Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:

Flip all the lights.
Flip lights with even numbers.
Flip lights with odd numbers.
Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
Example 1:
Input: n = 1, m = 1.
Output: 2
Explanation: Status can be: [on], [off]
Example 2:
Input: n = 2, m = 1.
Output: 3
Explanation: Status can be: [on, off], [off, on], [off, off]
Example 3:
Input: n = 3, m = 1.
Output: 4
Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
Note: n and m both fit in range [0, 1000].*/

class Solution {
    public int flipLights(int n, int m) {
        if(n <= 0 || m < 0) return 0; //无效返回0
        if(m == 0) return 1; //没有操作只有一种状态
        if(n == 1) return 2; //一只灯泡无论多少次操作至多两个状态
        if(n==2 && m==1) return 3; //限定m == 1
        if(n == 2) return 4; //比上面多一个全开
        if(m == 1) return 4; //1,2,3,4状态
        if(m == 2) return 7; //得不到单4的状态
        if(m >= 3) return 8; //可以组合出这8种状态
        return 8;
    }
    /*We only need to consider special cases which n<=2 and m < 3. When n >2 and m >=3, the result is 8.
    The four buttons:

    Flip all the lights.
    Flip lights with even numbers.
    Flip lights with odd numbers.
    Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
    If we use button 1 and 2, it equals to use button 3.
    Similarly...

    1 + 2 --> 3, 1 + 3 --> 2, 2 + 3 --> 1
    So, there are only 8 cases.

    All_on, 1(就是All_off), 2, 3, 4, 1+4, 2+4, 3+4

    And we can get all the cases, when n>2 and m>=3.*/
}
