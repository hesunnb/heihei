/*Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a 
subsequence and not a substring.*/


class Solution {
    //test case就想: abcabcbb, pwwkew就行了
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0, counter = 0, d = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) > 1) counter++; //一旦有重复字母, counter马上++
            end++; //在遇到重复字母之前end一直往后走, d一直都保留不重复的最大长度, 注意d在里面的while循环之外, 所以只要没有重复
            //字母出现的时候d的值一直都会被计算
            
            while (counter > 0) { //当有重复字母的时候马上进入这个循环, 处理有重复字母的情况
                char tempc = s.charAt(begin);
                if (map.get(tempc) > 1) counter--; //遇到重复字母counter就--, 然后去掉重复字母, 此时end与begin之间就没有重
                //复字母了然后退出此while循环, 从新计算d的长度
                map.put(tempc, map.get(tempc)-1);
                begin++;
            }
            d = Math.max(d, end - begin); //end与begin之间一直都是map里面的所有字母, 有重复字母的时候就会进入子while循环去重, 
            //没有重复字母的时候就计算d取每次的最大值
        }
        return d;
    }
}
