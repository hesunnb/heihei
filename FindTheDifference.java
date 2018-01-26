/*Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.*/


class Solution {
    /*The logic behind doing XOR is that if you XOR two identical values then its result is zero. Moreover XOR is associative: what this     means is:
    x ^ (y ^ z) = (x ^ y) ^ z. Therefore if we XOR the characters in strings s and t and suppose s = ‘abcd’ and t = ‘abcde’, then:
    (a ^ b ^ c ^ d) ^ (a ^ b ^ c ^ d ^ e) = (a ^ a) ^ (b ^ b) ^ (c ^ c) ^ (d ^ d) ^ e = 0 ^ 0 ^ 0 ^ 0 ^ e = e
    Therefore by XOR’ing all characters in strings s and t the end result you get is the extra character added in t*/
    
    public char findTheDifference(String s, String t) {
        
        if(s == null || t == null) {
            return 'X';
        }
        
        char c = t.charAt(s.length()); //让c直接是t中的最后那一个多出来的字母
        for (int i = 0; i < s.length(); i++) { //对于s的长度从头异或到尾
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }
    /*If you use c = c ^ s.chatAt(i) instead of c ^= s.charAt(i),you have to code like:
    (because use " ^= " Java code can change type automatically)
    public char findTheDifference(String s, String t) {
       int c = 0;
       for (int i = 0; i < s.length(); ++i) {
	        c = c ^ s.charAt(i);
       }
       for (int i = 0; i < t.length(); ++i) {
	        c = c ^ t.charAt(i);
       }
       return (char)c;
     }*/
}
