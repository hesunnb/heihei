/*Count the number of k's between 0 and n. k can be 0 - 9.
Example

if n = 12, k = 1 in

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

we have FIVE 1's (1, 10, 11, 12)
*/

class Solution {
    /*
     * param k : As description.
     * param n : As description.
     * return: An integer denote the count of digit k in 1..n
     */
     
    //version1: 字符串处理
    public int digitCounts(int k, int n) {
        // write your code here
        
        if(n < 0) {
            return 0;
        }
        int count = 0;
        while(n >= k) {
            String temp = String.valueOf(n);
            for(int i = 0; i < temp.length(); i++) {
                if(temp.charAt(i) - '0' == k) {
                    count++;
                }
            }
            n--;
        }
        return count;
    }
    
    
    //version2: 求余处理
    public int digitCounts(int k, int n) {
        // write your code here
        int cnt = 0;
        for (int i = k; i <= n; i++) {
            cnt += singleCount(i, k);
        }
        return cnt;
    }
    
    private int singleCount(int i, int k) {
        if (i == 0 && k == 0) { //找0的时候下面是i > 0才循环, 循环不到, 所以这里多加一个只对0的判断
            return 1;
        }

        int cnt = 0;
        while (i > 0) {
            if (i % 10 == k) {
                cnt++;
            }
            i = i / 10;
        }
        return cnt;
    }
};
