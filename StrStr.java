/*Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.*/
/*For a given source string and a target string, you should output the first index(from 0) of target string in source string.

If target does not exist in source, just return -1.*/

public class Solution {
    public int strStr(String haystack, String needle) {
        
        if(haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        
        int i = 0, j = 0;
        for(i = 0; i <= haystack.length() - needle.length(); i++) {
            for(j = 0; j < needle.length(); j++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            
            if(j == needle.length()) {
                return i;
            }
        }
        
        return -1;
    }
}
