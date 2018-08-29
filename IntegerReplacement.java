/*Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:

Input:
8

Output:
3

Explanation:
8 -> 4 -> 2 -> 1
Example 2:

Input:
7

Output:
4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1*/

class Solution {
    public int integerReplacement(int n) {
        
        //+1与-1如果能产生相同的1说明+1一定有进位, 而且只进1位, 比如11011加1是11100, 减一是11010, 而这种情况+1一定会产生trailing zero, 所以要+1
        //如果-1比+1之后产生的1要少, 说明-1会产生trailing zero, 比如11101, 其实都只看最后2位就行
        //剩下的情况都是+1会产生更多的0, 这些的前提都是当前所在位的值是1, 是0就直接除了
        int c = 0;
        while (n != 1) { //最后一定是10 -> 1, 因为11也要变成10再到1
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || ((n >>> 1) & 1) == 0) {
                n--;
            } else {
                n++;
            }
            c++;
        }
        return c;
    }
    /*The first step towards solution is to realize that you're allowed to remove the LSB only if it's zero. And to reach the target 
    as fast as possible, removing digits is the best way to go. Hence, even numbers are better than odd. This is quite obvious.

    What is not so obvious is what to do with odd numbers. One may think that you just need to remove as many 1's as possible to 
    increase the evenness of the number. Wrong! Look at this example:

    111011 -> 111010 -> 11101 -> 11100 -> 1110 -> 111 -> 1000 -> 100 -> 10 -> 1
    And yet, this is not the best way because

    111011 -> 111100 -> 11110 -> 1111 -> 10000 -> 1000 -> 100 -> 10 -> 1
    See? Both 111011 -> 111010 and 111011 -> 111100 remove the same number of 1's, but the second way is better.

    So, we just need to remove as many 1's as possible, doing +1 in case of a tie? Not quite. The infamous test with n=3 fails for that
    strategy because 11 -> 10 -> 1 is better than 11 -> 100 -> 10 -> 1. Fortunately, that's the only exception (or at least I can't 
    think of any other, and there are none in the tests).

    So the logic is:

    If n is even, halve it.
    If n=3 or n-1 has less 1's than n+1, decrement n.
    Otherwise, increment n.
    
    Here is an example of such a solution in Java:

    public int integerReplacement(int n) {
        int c = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n >>>= 1;
            } else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1)) {
                --n;
            } else {
                ++n;
            }
            ++c;
        }
        return c;
    }
    这里就是结论: 以后这道题就这么做, 这么想就行了
    Of course, doing bitCount on every iteration is not the best way. It is enough to examine the last two digits to figure out 
    whether incrementing or decrementing will give more 1's. Indeed, if a number ends with 01, then certainly decrementing is the 
    way to go. Otherwise, if it ends with 11, then certainly incrementing is at least as good as decrementing (*011 -> *010 / *100) 
    or even better (if there are three or more 1's). This leads to the following solution.
    */
}