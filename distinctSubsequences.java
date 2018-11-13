/*Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^

*/

public class Solution {

    public int numDistinct(String S, String T) {

        //思路:动规，题意就是在S当中能挑出多少个T
        if(S == null || T == null) {
            return 0;
        }
       
        int[][] result = new int[S.length() + 1][T.length() + 1];
        for(int i = 0; i <= S.length(); i++) {
            result[i][0] = 1; //在s当中挑出t的空串总有一种方案；第一行除了第一个默认就是0(j > 0), 意思就是在空串当中挑不出来T当中的内容; 
            //这也是比较独特的初始化i和j不一样的一道题
        }
        
        for(int i = 1; i <= S.length(); i++) { //S或者T中有空串的情况也包括在内了，有空串循环有一个肯定不能执行，然后直接返回值
            //(第一行尾或第一列尾)
            for(int j = 1; j <= T.length(); j++) {
                result[i][j] = result[i - 1][j]; //保留上次匹配的结果，在S未加入新字母之前的结果
                if(S.charAt(i - 1) == T.charAt(j - 1)) {
                    result[i][j] = result[i - 1][j - 1] + result[i - 1][j]; //左上+上
                }
            }
        }
        return result[S.length()][T.length()];
        
        //f[i][j]表示S的前i个字符中选取T的前j个字符, 有多少种方案
        /*f[i][j] = f[i-1][j] + f[i-1][j-1] (S[i-1]==T[j-1])
                  = f[i-1][j]               (S[i-1]!=T[j-1]) //这个就是方程, 按照文字方法读出来就好理解多了
        //前i个字符中选取T的前j个字符 = 前i-1个字符中选取T的前j个字符 + 前i-1个字符中选取T的前j-1个字符...
        */
        /**
         *       r a b b i t (T串)
         *     1 0 0 0 0 0 0
         *   r 1 1 0 0 0 0 0 
         *   a 1 1 1 0 0 0 0 
         *   b 1 1 1 1 0 0 0 
         *   b 1 1 1 2 1 0 0 
         *   b 1 1 1 3 3 0 0 
         *   i 1 1 1 3 3 3 0 
         *   t 1 1 1 3 3 3 3
         * 　(S串)
         *
         *       b a g (T串)
         *     1 0 0 0 
             b 1 1 0 0 
             a 1 1 1 0 
             b 1 2 1 0 
             g 1 2 1 1 
             b 1 3 1 1 
             a 1 3 4 1      比如: 现在到了T:ba, S:babgba的时候, 那么S新加入的是a, 按照方程怎么理解呢: 首先就是babgb(i-1)包含多少个ba, 然后babgb
             g 1 3 4 5            中的a完全可以用S新加入的a替换, 就相当于完全拷贝了babgb包含ba的结果; 其次就是babgb(i-1)包含多少个b(j-1), 
             (S串)                然后当S和T同时加入a的时候(都到了i,j), 与之前(i-1)结果也是相当, 因为a相同, 加入不会改变之前包含的结果, 所以也是
                                  拷贝相加
         */
    }
}
