/*Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/

public class Solution {

    public int minDistance(String word1, String word2) {        
        //思路：动规
        if(word1 == null || word2 == null) {
            return 0;
        }
        
        int[][] result = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i++) { //让空串变成目标串就向里面加字母
            result[i][0] = i;
        }
        for(int j = 0; j <= word2.length(); j++) { //让已知串变成目标空串就不断减字母
            result[0][j] = j;
        }
        
        //如果word1或者word2的长度为0, 下面这两个循环都会有一个不执行, 从而直接返回结果
        for(int i = 1; i <= word1.length(); i++) {
            for(int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    result[i][j] = result[i - 1][j - 1]; //因为加进来的一样，所以跟都去掉也是一样的
                }
                else {
                    result[i][j] = 1 + Math.min(result[i - 1][j - 1], Math.min(result[i][j - 1], result[i - 1][j]));
                    //由这三个值当中的任一种都可计算出result[i][j]; 比如①m到ka, 要两步, 接下来一个加r, 一个加a, 然后把a变成r, 加一步, 总共三步　
                    //②m到kar, 先是三步, 然后m下面的a加上来, 多余, 删掉, 加一步, 总共四步③ma到ka, 就一步, 然后ma再加r, 加一步, 总共两步
                }
            }
        }
        
        return result[word1.length()][word2.length()];
        /**
         *      k a r m a
         *    0 1 2 3 4 5
         *  m 1 1 2 3
         *  a 2 2 1 2           //如果ａ[i] != b[j]: 这行2这个点(就是最右下角的那个２), 
         *  r 3                 可以由它左边, 左上, 上边３个点分别计算而来, 那么谁
         *  t 4                 花的步数小, 谁就是最优的
         *                      //如果ａ[i] == b[j]: 它就等同于没有i和j这两个点加进来时候的值, 
         *  　　　　　　　　　　 因为此时i和j加进来不会增加操作, 因为仍然是转换好了的值
         *
         * 
         */                     
    }
}
