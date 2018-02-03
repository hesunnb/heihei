/*Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity 
O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.*/


class Solution {
    //使用substring问题模板, 注释见FindAllAnagramsInAString.java
    public String minWindow(String s, String t) {
        String result = "";
        if(s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return result;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size(); 
        int begin=0, end = 0, len = Integer.MAX_VALUE; //多个len变量
        while(end < s.length()) { 
            char c = s.charAt(end);
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1); 
                if(map.get(c) == 0) { 
                    counter--;
                }
            }
            end++;
            
            while(counter == 0) { 
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1); 
                    if(map.get(tempc) > 0) {
                        counter++;
                    }
                }
                if(counter > 0) { 
                    if(end - begin <= len) {
                        result = s.substring(begin, end); //因为要找最小, 所以第一次用Integer.MAX_VALUE找出最开始符合条件
                        //的那个
                        len = result.length(); //然后以截取出来的子串的长度缩短len的值, 知道最后找出minimum
                    }
                }
                begin++;
            } 
        }
        return result;
    }
}
