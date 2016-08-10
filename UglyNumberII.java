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
            if (ugly == multi2) {
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

*/
