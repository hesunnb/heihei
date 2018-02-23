/*Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like 
the image below.


American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.*/


class Solution {

    //solution1:用哈希表存row数好一点, 查询能更快一些
    public String[] findWords(String[] words) {
        
        if(words == null || words.length == 0) {
            return new String[0];
        }
        
        String[] strs = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < strs.length; i++){
            for(char c : strs[i].toCharArray()){
                map.put(c, i); //put <char, rowIndex> pair into the map, 字母与行数组成哈希表
            }
        }
        
        List<String> res = new ArrayList<>();
        for(String w : words){
            if(w.equals("")) continue; //这个还真不能少, 因为如果map里面要是没有"", 那么get("")得到的就是null, 下面int index = null就报错了
            int index = map.get(w.toLowerCase().charAt(0)); //根据第一个字母来看这个字符串应该属于哪个row
            for(char c : w.toLowerCase().toCharArray()) {
                if(map.get(c) != index) {
                    index = -1; //don't need a boolean flag. 
                    break;
                }
            }
            
            if(index != -1) { //if index != -1, this is a valid string
                res.add(w);
            }
        }
        
        return res.toArray(new String[0]);
    }
    
    
    //solution3:(own)
    public String[] findWords(String[] words) {
        
        if(words == null || words.length == 0) {
            return new String[0];
        }
        
        List<String> list = new ArrayList<>();
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";
        
        for(int i = 0; i < words.length; i++) {
            int j = 0;
            for(j = 0 ; j < words[i].length(); j++) { //扫描row1
                if(!row1.contains(String.valueOf(Character.toLowerCase(words[i].charAt(j))))) break;
            }
            if(j == words[i].length()) { //如果扫到尾了, 说明是
                list.add(words[i]);
                continue;
            }
            
            for(j = 0 ; j < words[i].length(); j++) { //一样
                if(!row2.contains(String.valueOf(Character.toLowerCase(words[i].charAt(j))))) break;
            }
            if(j == words[i].length()) {
            	list.add(words[i]);
                continue;
            }
            
            for(j = 0 ; j < words[i].length(); j++) { //一样
                if(!row3.contains(String.valueOf(Character.toLowerCase(words[i].charAt(j))))) break;
            }
            if(j == words[i].length()) {
            	list.add(words[i]);
                continue;
            }
        }
        
        return list.toArray(new String[0]); //toArray方法用泛型这个, 传入数组小于集合长度会新建一个数组进行拷贝,
        //向传入数组中拷贝
    }
}
