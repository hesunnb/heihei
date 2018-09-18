/*Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first 
string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].*/

class Solution {

    //substring相关问题用统一
    //minimum-window-substring
    //longest-substring-without-repeating-characters
    //substring-with-concatenation-of-all-words
    //longest-substring-with-at-most-two-distinct-characters
    //find-all-anagrams-in-a-string
    //Permutation-in-String
    public boolean checkInclusion(String s1, String s2) {
		
	if(s1 == null || s2 == null || s2.length() < s1.length()) {
		return false;
	}
		
	Map<Character, Integer> map = new HashMap<>();
        for(char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1); //getOrDefault是jdk8的新函数, 如果map中没有c字符, 那么会返回一个默认值0
            //(这个默认值是自己规定的), 如果有c字符, 就得到c字符对应的值
        }
        int counter = map.size(); //map.size()就是数有多少个键值对
        int begin=0, end = 0;
        while(end < s2.length()) { //先让end使劲往后走
            char c = s2.charAt(end);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1); //如果map当中有这个字母就减1
                if(map.get(c) == 0) { //每当有一个字母的数量变为0的时候, map的大小就减1, 表名end与begin之间的字符串已经完全包含
                //了p中的所有的这个字母
                    counter--;
                }
            }
            end++;
            
            while(counter == 0) { //当counter等于0的时候, 说明end与begin之间已经完全包含了p这个字符串里面的所有字符
                char tempc = s2.charAt(begin);
                if(map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1); //然后begin往后走, 遇到map中包含的字母就加1
                    if(map.get(tempc) > 0) {
                        counter++;
                    }
                }
                if(counter > 0) { //当counter大于0的时候说明end与begin之间有一个p中的字母要被排出在end与begin之间了, 就是begin处所在的字符
                    if(end - begin == s1.length()) { //此时就判断在排出之前end与begin的差是否与p的长度相等
                        return true;
                    }
                }
                begin++;
            } //退出这个counter==0的循环说明end与begin之间已经不完全包含p的字母, 这时候就会到顶上的while循环end接着往后走包含进新
            //的字母
        }
        return false;
    }
}
