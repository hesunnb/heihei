/*Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.*/

class Solution {

    //solution2:(own), 与Isomorphic Strings同构字符串这道题意思非常接近, 属于类型题
    public boolean wordPattern(String pattern, String str) {
        
        if(pattern == null || str == null) {
            return false;
        }
        
        String[] sArr = str.split(" ");
        if(sArr.length != pattern.length()) {
        	System.out.println(1);
        	return false;
        }
        
        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < pattern.length(); i++) {
        	if(!map.containsKey(pattern.charAt(i))) {
        		if(map.containsValue(sArr[i])) {
        			return false;
        		}
        		map.put(pattern.charAt(i), sArr[i]);
        	}
        	else {
        		if(!map.get(pattern.charAt(i)).equals(sArr[i])) {
        			return false;
        		}
        	}
        }
        return true;
    }
}
