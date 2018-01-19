/*Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.*/

class Solution {

    //solution1:
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) {
	    return false;
	}
        
        int[] m1 = new int[256]; //用于装256个ASCII码字符
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) { //被成对标记过的数字应该是一样的, 如果不一样说明一个字母配过对, 
            //而一个没有配过, 就违反规则了
                return false;
            }
            m1[s.charAt(i)] = i + 1; //因为数组初始化之后都是0, i+1是为了和0区分开, 表示这个字母被标记过了
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
    
    
    //solution2:(own)
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) {
	    return false;
	}
		
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            if(!map.containsKey(s.charAt(i))) {
                if(map.containsValue(t.charAt(i))) { //表里没有就成对放, 放之前检查一下值列表里有没有重复
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            } else {
                if(map.get(s.charAt(i)) != t.charAt(i)) { //如果表里已经有key, 就检查key对应的value值一不一样
                    return false;
                }
            }
        }
        return true;
    }
}
