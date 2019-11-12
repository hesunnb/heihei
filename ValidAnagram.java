/*Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?*/

public class Solution {

    public boolean anagram(String s, String t) {
        // write your code here
        
        if(s.length() != t.length()) {
            return false;
        }
        
        int[] result = new int[256];
        for(int i = 0; i < s.length(); i++) {
            result[(int)s.charAt(i)]++;
        }
        
        for(int i = 0; i < t.length(); i++) {
            
            if(--result[(int)t.charAt(i)] < 0) {
                return false;
            }
        }
        
        return true;
    }
    
    //followup就把数组变成哈希表存unicode就行, 空间从O(1)变为O(n)
};
