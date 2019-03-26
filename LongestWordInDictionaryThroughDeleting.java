/*Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of 
the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. 
If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.*/

class Solution {
    
    //方法没什么特殊的, 就是排序这个写法值得学习
    public String findLongestWord(String s, List<String> d) {
        if(s == null || d == null || d.size() == 0) {
            return "";
        }
        
        //把dict排个序
        Collections.sort(d, (x, y) -> x.length() != y.length() ? -Integer.compare(x.length(), y.length()) : x.compareTo(y));
        for(String word : d) { //然后逐个比较
            int j = 0;
            for(int i = 0; i < s.length(); i++) {
                if(j < word.length() && s.charAt(i) == word.charAt(j)) {
                    j++;
                }
            }
            if(j == word.length()) {
                return word;
            }
        }
        return "";
    }
}
