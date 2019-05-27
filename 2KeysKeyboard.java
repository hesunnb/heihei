/*Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
 

Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum 
number of steps to get n 'A'.

Example 1:

Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
 

Note:

The n will be in the range [1, 1000].*/

class Solution {
    
    public int minSteps(int n) {
        if(n <= 1) {
            return 0;
        }
        
        int sum = 0;
        for(int i = 2; i <= n; i++) {
            /*质数除了自己本身是不可能被i整除的, 所以质数只能由A自己复制粘贴得来*/
            while(n % i == 0) { /*i代表复制几次, 比如n是27, i是3, 27/3就是以商9复制3次得来(一次复制两次粘贴), 9/3是以商3复制3次得来, 3自己是
                质数, 所以质数就是质数次*/
                sum += i; //sum加上复制的次数
                n /= i; //n变为需要复制i次的商
            }
        }
        return sum;
    }
}
