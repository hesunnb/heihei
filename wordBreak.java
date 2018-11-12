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
    //solution1: 就这个最好
    public boolean wordBreak(String s, Set<String> dict) {
        
        //思路: 动规, 因为dict是词典, 其中会有最长的单词, 所以枚举长度就行(比如ｓ的长度是100万, 你不用像palindrome partitioning 2那道题一样, 
        //从头枚举，只向前看最大长度的坐标就到头了)
        
        if(s == null || s.length() == 0) {
            return true; //在s为""的时候, 它的dict也是[], dict不会出现s里面没有的东西, 所以要为true
        }
        
        boolean[] canSegment = new boolean[s.length() + 1]; //长度+1, 也是前几个的意思
        int maxLength = getMaxLength(dict);
        
        canSegment[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            canSegment[i] = false; //每个新地方初始为false
            for(int lastwordlength = 1; lastwordlength <= maxLength && lastwordlength <= i; lastwordlength++) {
                if(!canSegment[i - lastwordlength]) { //true代表能切到的地方, false就是不能切到的地方, 所以都要从true的位置开始取substring
                    continue;
                }
                //true的位置会取substring, 如果dict不包含这个substring, 还会接着往头部找别的true取substring判断(一直向头走, 遇到true就取
                //substring)判断
                String word = s.substring(i - lastwordlength, i); //这个取substring也是倒着往头部取, 长度越来越长
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
    
    
    //solution2: 正着找的版本, 会MLE
    public boolean wordBreak(String s, List<String> dict) {
        
        if(s == null || s.length() == 0) {
            return true;
        }
        
        boolean[] canSegment = new boolean[s.length() + 1];
        
        canSegment[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            canSegment[i] = false; 
            for(int lastwordlength = 0; lastwordlength < i; lastwordlength++) {
                if(!canSegment[lastwordlength]) { 
                    continue;
                }
                
                String word = s.substring(lastwordlength, i); //这回是正着取substring, 如果没有maxLength的限制, 有一个问题就是这个substring
                //可能会取的很大很长, 会有MLE的可能
                if(dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }
        return canSegment[s.length()];
    }
    //回文串正着找倒着找没区别的原因是, 回文串没有长度限制, 多长的都有; 这道题它是看包含, 在dict中已经有了对比的对象, 
    //所以倒着找超过maxLength就break省时, 如果正着找就用不到maxLength了, 如果用maxLength就得写成从i-maxLength处开始正着找, 
    //那么这个和倒着找就没区别了
    
    
    //solution3: 正着找的版本的略微优化, 也会TLE
    public boolean wordBreak(String s, Set<String> dict) {
        
        if(s == null || s.length() == 0) {
            return true;
        }
        
        boolean[] canSegment = new boolean[s.length() + 1];
        int maxLength = getMaxLength(dict);
        
        canSegment[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            canSegment[i] = false; 
            for(int lastwordlength = 0; lastwordlength < i; lastwordlength++) {
                if((i - lastwordlength) > maxLength || !canSegment[lastwordlength]) { //正着找的时候虽然在这里面进行了优化, 
                    //就是i与lastwordlength的长度在maxLength之间我才会去取substring, 这样不会MLE, 但是因为每次都从头开始扫, 所以复杂度
                    //接近全o(n ^ 2), 会TLE; 但是倒着找, 每次最多找maxLength的长度, 就不会TLE
                    continue;
                }
                
                String word = s.substring(lastwordlength, i); //这回是正着取substring
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
