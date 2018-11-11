/*Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a 
space-separated sequence of one or more dictionary words.

Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

public class Solution {

    //一个例子: s="aaaaaaa", list{"aaaa", "aaa"}, 如果要是从头扫s, list包含一个子串就去掉, 这样返回的结果就不对, 因为"aaa","aaa","a"就返回
    //false了
    public boolean wordBreak(String s, Set<String> dict) {
        
        //思路: 动规, 因为dict是词典, 其中会有最长的单词, 所以枚举长度就行(比如ｓ的长度是100万, 你不用像palindrome partitioning 2那道题一样, 
        //从头枚举，只向前看最大长度的坐标就到头了)
        
        if(s == null || s.length() == 0) {
            return true; //在s为""的时候, 它的dict也是[], dict不会出现s里面没有的东西, 所以要为true
        }
        
        boolean[] canSegment = new boolean[s.length() + 1];
        int maxLength = getMaxLength(dict);
        
        canSegment[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            //让单词长度不超过dict中最大的词长, 在从头找的过程中要是有一次没有满足在maxLength的长度内找到一个在dict中的单词, 
            //那么在下一个canSegment[i]时, 它的前maxLength个就会都是false, 从而不会去进行下面的substring的判断
            for(int lastwordlength = 1; lastwordlength <= maxLength && lastwordlength <= i; lastwordlength++) {
                if(!canSegment[i - lastwordlength]) { //它会取到最近的一个true来进行下面的substring的判断
                    continue;
                }
                
                String word = s.substring(i - lastwordlength, i);
                if(dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }
        return canSegment[s.length()];
    }
    
    private int getMaxLength(Set<String> dict) {
        int maxLength = 0;
        for(String word : dict) { //每次从dict中取出一个元素给word，直到没有元素为止
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }
}
