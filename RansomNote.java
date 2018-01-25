/*Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return 
true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true*/


class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
        if(ransomNote == null || magazine == null) {
            return false;
        }
        
        int[] store = new int[26]; //思路很直接, 按位置标记就行了
        for(int i = 0; i < magazine.length(); i++) {
            store[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0; i < ransomNote.length(); i++) {
            store[ransomNote.charAt(i) - 'a']--;
            if(store[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
