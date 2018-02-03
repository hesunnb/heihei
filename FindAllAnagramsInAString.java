/*Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/


class Solution {
    //关于substring的问题有统一模板, 这个就是使用的模板
    public List<Integer> findAnagrams(String s, String p) {
        
        List<Integer> result = new ArrayList<>();
        if(s == null || p == null || s.length() == 0 || p.length() == 0 || p.length() > s.length()) {
            return result;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1); //getOrDefault是jdk8的新函数, 如果map中没有c字符, 那么会返回一个默认值0
            //(这个默认值是自己规定的), 如果有c字符, 就得到c字符对应的值
        }
        int counter = map.size(); //map.size()就是数有多少个键值对
        int begin=0, end = 0;
        while(end < s.length()) { //先让end使劲往后走
            char c = s.charAt(end);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1); //如果map当中有这个字母就减1
                if(map.get(c) == 0) { //每当有一个字母的数量变为0的时候, map的大小就减1, 表名end与begin之间的字符串已经完全包含
                //了p中的所有的这个字母
                    counter--;
                }
            }
            end++;
            
            while(counter == 0) { //当counter等于0的时候, 说明end与begin之间已经完全包含了p这个字符串里面的所有字符
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1); //然后begin往后走, 遇到map中包含的字母就加1
                    if(map.get(tempc) > 0) {
                        counter++;
                    }
                }
                if(counter > 0) { //当counter大于0的时候说明end与begin之间有一个p中的字母要被排出在end与begin之间了
                    if(end - begin == p.length()) { //此时就判断在排出之前end与begin的差是否与p的长度相等
                        result.add(begin);
                    }
                }
                begin++;
            } //退出这个counter==0的循环说明end与begin之间已经不完全包含p的字母, 这时候就会到顶上的while循环end接着往后走包含进新
            //的字母
        }
        return result;
    }
}
