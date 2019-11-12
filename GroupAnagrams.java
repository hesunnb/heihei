/*Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.*/

public class Solution {
    
    //复杂度是O(nk), n是strs的长度, k是里面每个字符串的长度
    public List<List<String>> groupAnagrams(String[] strs) {
        
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0) {
           return result;
        }
        
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for(String str : strs) {
            int[] count = new int[26]; //只有小写字母
            for(int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            
            int hash = getHash(count); //每一个字符串都会得到一个count数组, 每个count数组都要算一个hash值
            if(!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>()); //没有就新创建一个ArrayList
            }
            
            map.get(hash).add(str); //在相应的hash位置加入
        }
        
        for(List<String> temp : map.values()) {
            result.add(temp); //这道题里一个元素自己也算数, 如那个"bat"
        }
        return result;
    }
    
    //RShash函数, 这是标准写法, 产生不同的hash值
    private int getHash(int[] count) {
        
        int hash = 0;
        int a = 378551; //这两个数是固定的, 能产生不冲突的hash值
        int b = 63689;
        
        for(int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }
}


public class Solution {
    
    // O(Nklogk) n是strs长度, klogk是strs里面每个字符串都排了序 
    public List<List<String>> groupAnagrams(String[] strs) {
        
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0) {
            return result;
        }
        
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String str : strs) {
            char[] temp = str.toCharArray(); //把字符串转成数组
            Arrays.sort(temp); //排序
            String newStr = String.valueOf(temp); //再转成字符串
            
            if(!map.containsKey(newStr)) { //不包含这个新的字符串
                map.put(newStr, new ArrayList<String>()); //新创建一个
            }
            
            map.get(newStr).add(str); //在相应位置加入
        }
        
        for(List<String> temp : map.values()) {
            result.add(temp); //取得最终结果
        }
        
        return result;
    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0) {
           return result;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            int[] count = new int[26]; //只有小写字母
            Arrays.fill(count, 0);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            
            for(int i = 0; i < count.length; i++) { //如果不用getHash()这种方式, 可以用像木桶排序这种方式, 重新拼接字符串, 也是排了序
                while(count[i] > 0) {
                    sb.append((char)('a' + i));
                    count[i]--;
                }
            }
            String keyString = sb.toString();
            if(!map.containsKey(keyString)) {
                map.put(keyString, new ArrayList<String>()); //没有就新创建一个ArrayList
            }
            
            map.get(keyString).add(str); //在相应的hash位置加入
        }
        
        for(List<String> temp : map.values()) {
            result.add(temp); //这道题里一个元素自己也算数, 如那个"bat"
        }
        return result;
    }
}
