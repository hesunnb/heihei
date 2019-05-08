/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence 
where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/

class Solution {
  
    //使用记忆化搜索, 正常搜索会造成TLE, 用map存住之前生成的结果, 然后拿回来直接使用
    public List<String> wordBreak(String s, List<String> wordDict) {
        
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }
        
        return helper(s, wordDict, new HashMap<String, List<String>>());
    }
    
    public List<String> helper(String s, List<String> wordDict, Map<String, List<String>> map) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        
        List<String> result = new ArrayList<>();
        if(s.length() == 0) {
            result.add("");
            return result;
        }
        
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                List<String> step = helper(s.substring(word.length()), wordDict, map);
                for(String str : step) {
                    if(str.equals("")) {
                        result.add(word);
                    } else {
                        result.add(word + " " + str);
                    }
                }
            }
        }
        
        map.put(s, result);
        return result;
    }
  
    /*例子的运行结果: 方便直接看代码
    {dog=[dog]}
    {sanddog=[sand dog], dog=[dog]}
    {anddog=[and dog], sanddog=[sand dog], dog=[dog]}
    {anddog=[and dog], sanddog=[sand dog], dog=[dog], catsanddog=[cat sand dog, cats and dog]}

    {apple=[apple]}
    {apple=[apple], penapple=[pen apple]}
    {apple=[apple], penapple=[pen apple], applepenapple=[apple pen apple, applepen apple]}
    {apple=[apple], pineapplepenapple=[pine apple pen apple, pine applepen apple, pineapple pen apple], 
    penapple=[pen apple], applepenapple=[apple pen apple, applepen apple]}*/
}




