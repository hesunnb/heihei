/*Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at 
a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical 
order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input: 
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: 
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].*/

class Solution {
    //testcase: {"vsw","vs","zwu","vsx","nc","o","vswus","orv","imf","i","v","zw","vs"}
    public String longestWord(String[] words) {
        
        if(words == null || words.length == 0) {
            return "";
        }
        
        Arrays.sort(words); //testcase sort之后: i imf nc o orv v vs vs vsw vswus vsx zw zwu
        Set<String> built = new HashSet<String>(); //set里面已经有v,vs了, 再来一个vs也会满足条件加到set中, 就是个去重的作用
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) { //长度为1的都会被加入到set中
                res = w.length() > res.length() ? w : res; //如果长度只有1的满足, 那也是最小的字母; vsw, vsx也会选到vsw, 因为sort之后vsw会
                //先出现, 先被加入到set然后给res, 之后的vsx的长度不大于vsw, 所以不会赋值给res
                built.add(w);
            }
        }
        return res;
    }
}
