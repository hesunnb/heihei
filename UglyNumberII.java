/*
Ugly number is a number that only have factors 2, 3 and 5.

Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
Notice

Note that 1 is typically treated as an ugly number.

Example

If n=9, return 10.
*/

class Solution {
    /**
     * @param n an integer
     * @return the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // Write your code here
        int[] res = new int[n];
        res[0] = 1;
        int next2 = 0;
        int next3 = 0;
        int next5 = 0;
        int multi2 = 0;
        int multi3 = 0;
        int multi5 = 0;
        for (int i = 1; i < n; i++) {
            multi2 = res[next2] * 2;
            multi3 = res[next3] * 3;
            multi5 = res[next5] * 5;
            int ugly = Math.min(multi2, Math.min(multi3, multi5));
            if (ugly == multi2) { //比如multi2和multi3同时和ugly相等的话, 两个的下标都要分别+1
                next2++;
            }
            if (ugly == multi3) {
                next3++;
            }
            if (ugly == multi5) {
                next5++;
            }
            res[i] = ugly;
        }
        return res[n - 1];
    }
};

/*
丑数是由丑数乘出来的, 所以目标就是让每个丑数都分别乘以2,3,5, 然后比较它们的大小
(*2)multi2 =  2         next2 = 1
(*3)multi3 =  3         next3 = 0
(*5)multi5 =  5         next5 = 0
在multi2的地方每次都成以2, 在multi3的地方每次都乘以3, 在multi5的地方每次都乘以5
每次next2, next3, next5所在的下标的值如果最小成为了丑数, 就把下标+1, 用下一个丑数接着运算
*/


//这些是向super ugly number过度的几个版, 就是把值都存到数组里操作
public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int[] next = new int[3];
        int[] multi = new int[3];
        
        for (int i = 1; i < n; i++) {
            multi[0] = res[next[0]] * 2;
            multi[1] = res[next[1]] * 3;
            multi[2] = res[next[2]] * 5;
            int ugly = Math.min(multi[0], Math.min(multi[1], multi[2]));
            if (ugly == multi[0]) {
                next[0]++;
            }
            if (ugly == multi[1]) {
                next[1]++;
            }
            if (ugly == multi[2]) {
                next[2]++;
            }
            res[i] = ugly;
        }
        return res[n - 1];
    }
    
    
    public int nthUglyNumber(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int[] next = new int[3];
        int[] multi = new int[3];
        int[] primes = {2,3,5};
        
        int ugly = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for(int j = 0; j < 3; j++) {
                multi[j] = res[next[j]] * primes[j];
                if(multi[j] < ugly) {
                    ugly = multi[j];
                }
            }
            for(int j = 0; j < 3; j++) {
                if(ugly == multi[j]) {
                    next[j]++;
                }
            }
            res[i] = ugly;
            ugly = Integer.MAX_VALUE;
        }
        return res[n - 1];
    }
