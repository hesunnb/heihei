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

    //solution1:(用Objects.equals()方法解决了int i循环的问题)
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str == null) {
            return false;
        }
        
        String[] sArr = str.split(" ");
        if(sArr.length != pattern.length()) {
        	return false;
        }
        
        Map map = new HashMap();
        for(int i = 0; i < pattern.length(); i++) {
            //配对的双方的下标应该同时变化, 如果不同时变化就说明不符合模式条件; 然后哈希表的put方法对相同的key放入不同的值的时候会返回之前的
            //old value
            if(!Objects.equals(map.put(pattern.charAt(i), i), map.put(sArr[i], i))) { 
                //在这里使用Objects.equals()方法解决了用int i循环的问题
                //Objects.equals()比较的是值与对象类型(一些细节比较附在下面), 同时也能比较null与null的相等, 因为a.equals(b)这种方式null会出现
                //问题
                return false;
            }
        }
        return true;
    }
    
    
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
