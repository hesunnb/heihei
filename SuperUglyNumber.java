/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given 
primes = [2, 7, 13, 19] of size 4.

Notice

    1 is a super ugly number for any given primes.
    The given numbers in primes are in ascending order.
    0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000

Example

Given n = 6, primes = [2, 7, 13, 19] return 13
*/

public class Solution {
    /**
     * @param n a positive integer
     * @param primes the given prime list
     * @return the nth super ugly number
     */
     
    //根据ugly number2改编, 就是把next, multi和result都写到数组里面了
    public int nthSuperUglyNumber(int n, int[] primes) {
        // Write your code here
        
        if(primes == null || primes.length == 0 || n <= 0) {
            return 0;
        }
        
        int[] res = new int[n];
        res[0] = 1;
        int k = primes.length;
        int[] next = new int[k];
        int[] multi = new int[k];
        
        int ugly = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for(int j = 0; j < k; j++) {
                multi[j] = res[next[j]] * primes[j];
                if(multi[j] < ugly) {
                    ugly = multi[j];
                }
            }
            for(int j = 0; j < k; j++) {
                if(ugly == multi[j]) {
                    next[j]++;
                }
            }
            res[i] = ugly;
            ugly = Integer.MAX_VALUE;
        }
        return res[n - 1];
    }
}
