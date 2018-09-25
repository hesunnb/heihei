/*You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s 
that is a concatenation of each word in words exactly once and without any intervening characters.

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
    
    /*s = "abababab", words = ["ab", "ab", "ab"], 这个用strstr的方式就不行, 因为bababa和"ab", "ab", "ab"的a,b数目一致, 但是用strstr判断的
    时候bababa遇到ab就包含"ab", 所以会返回错误的结果*/
    /*s = "ababaab", words = ["ab", "ba", "ba"], 这个即使用strstr把找到的删除也不行babaab和"ab", "ba", "ba"的a,b数目也一致, 但是删除的时候,
    babaab先删除了ab就变成baab了, 但是还剩下"ba", "ba", 所以也会返回错误的结果*/
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0 || s.length() < words.length * words[0].length()) {
        	return result;
        }

        int wl = words[0].length(); //重复次数太多了, 太长了, 就用wl替代一下
        Map<String, Integer> map = new HashMap<>(); //words的map
        Map<String, Integer> curMap = new HashMap<>(); //s每一段子串的map
        
        for (String str : words) { //把words放入map
        	map.put(str, map.getOrDefault(str, 0) + 1);
        }
        
        String stepStr = null;
        String tmp = null;
        for (int i = 0; i < wl; i++) { //为什么要以wl作为最外层循环的次数: s = "barfoothefoobarman", words = ["foo","bar"], i从b开始一次, 
        //a一次, r一次从b开始, 所有的每3位(就是wl的长度)都会进行判断, 一直走到s的最后, a与r同理, 所以i要是从foo的f再走就重复了, 因为i从b开始走的
        //时候已经把这种情况包含进去了
            int count = 0;  // remark(备注): reset count 
            int start = i;
            for (int end = i; end + wl <= s.length(); end += wl) {
                stepStr = s.substring(end, end + wl); //end先往后走, 每次按照wl的长度取一截, 这也是为什么这道题要求words当中所有的单词长度要
                //都一致, 方便用循环如果长度不一致, 外层循环就只能一位一位往后走了, 取substring也不是固定长度, 就麻烦很多了
                if (map.containsKey(stepStr)) {
                    curMap.put(stepStr, curMap.getOrDefault(stepStr, 0) + 1);
                    
                    if (curMap.get(stepStr) <= map.get(stepStr)) {
                    	count++;
                    }
                    while (curMap.get(stepStr) > map.get(stepStr)) { //说明从start到end区间(实际是end+wl)有多余需要的了, 那么就要剔除
                        tmp = s.substring(start, start + wl);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        start += wl;
                        
                        //the same as https://leetcode.com/problems/longest-substring-without-repeating-characters/
                        if (curMap.get(tmp) < map.get(tmp)) count--; //tmp是从start就是头开始的, 所以要从头开始减到stepStr处才可以
                        
                    }
                    if (count == words.length) {
                        result.add(start);
                        tmp = s.substring(start, start + wl);
                        curMap.put(tmp, curMap.get(tmp) - 1); //把当前start处的一段给剔除掉, 然后start也要后窜
                        start += wl;
                        count--;
                    }
                } else { //一旦不包含, 就说明从start到end+wl这段地方都有这个不包含的子串, 所以就要跳过这段区域, start直接跳到end+wl处
                    curMap.clear();
                    count = 0;
                    start = end + wl;//not contain, so move the start
                }
            }
            curMap.clear(); //从新计算下一个i位置
        }
        return result;
    }
}
