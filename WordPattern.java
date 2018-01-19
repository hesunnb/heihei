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

    
    
    
    //solution2:(使用了Integer i进行循环, 避免了autoboxing-same-value-to-different-objects-problem的问题)
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null) {
            return false;
        }
        
        String[] sArr = str.split(" ");
        if(sArr.length != pattern.length()) {
        	return false;
        }
        
        Map map = new HashMap();
        //这里循环必须使用Integer i, int i不行, 即使规定了map的泛型<Object, Integer>也不行, 因为使用int i的时候, 放入哈希表中的时候会出现
        //autoboxing-same-value-to-different-objects-problem即相同值自动打包成了不同的对象, 而!=比较的是对象地址, 就会出错, 使用Integer的时候,
        //相同的值就会是同一个整型常量, 所以它们的地址也相同就ok
        for(Integer i = 0; i < pattern.length(); i++) {
            if(map.put(pattern.charAt(i), i) != map.put(sArr[i], i)) {
                return false;
            }
        }
        return true;
    }
    
    
    //solution3:(own), 与Isomorphic Strings同构字符串这道题意思非常接近, 属于类型题
    public boolean wordPattern(String pattern, String str) {
        
        if(pattern == null || str == null) {
            return false;
        }
        
        String[] sArr = str.split(" ");
        if(sArr.length != pattern.length()) {
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
