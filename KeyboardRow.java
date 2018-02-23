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
