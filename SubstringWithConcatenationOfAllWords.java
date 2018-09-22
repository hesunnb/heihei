/*You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodstudentgoodword",
  words = ["word","student"]
Output: []*/


class Solution {
    /*"foobarfoofoothefoofoobarfooman"
    ["foo","bar","foo"]
    Expected answer: [0,3,15,18], 允许words里面有重复的字符串, 但是这些字串必须长度都相同*/
    
    /*s = "abababab", words = ["ab", "ab", "ab"], 这个用strstr的方式就不行, 因为bababa和"ab", "ab", "ab"的a,b数目一致, 但是用strstr判断的时候
    bababa遇到ab就包含"ab", 所以会返回错误的结果*/
    /*s = "ababaab", words = ["ab", "ba", "ba"], 这个即使用strstr把找到的删除也不行babaab和"ab", "ba", "ba"的a,b数目也一致, 但是删除的时候,
    babaab先删除了ab就变成baab了, 但是还剩下"ba", "ba", 所以也会返回错误的结果*/
    public List<Integer> findSubstring(String s, String[] words) {
        
    }
}
