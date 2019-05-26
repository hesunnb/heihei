/*Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].*/

class Solution {
    
    /*转换成最大公共子序列问题.
    相当于找一个(ascii码和)最大的公共子序列, 然后从总的ascii和中减掉这部分. */
    public int minimumDeleteSum(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        
        int sum = 0;
        for(int i = 0; i < s1.length(); i++) {
            sum += s1.charAt(i);
        }
        for(int i = 0; i < s2.length(); i++) { //两个字符串的ASCII码和
            sum += s2.charAt(i);
        }
        
        int result[][] = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                result[i][j] = Math.max(result[i-1][j], result[i][j-1]);
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    result[i][j] = Math.max(result[i][j], result[i-1][j-1] + s1.charAt(i-1));
                }
            }
        }
        
        return sum - 2*result[s1.length()][s2.length()]; //因为sum是两个字符串的和, 所以要减去两遍
    }
}
