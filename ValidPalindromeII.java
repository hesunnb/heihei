/*Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.*/

class Solution {

    /*Just maintain 2 pointers i.e for start and end of string
    Keep checking if they are same

    If they are Same - then just check inside and keep going till you reach the center(left==right)(if odd string) or left>right 
    (if even string), If they are NOT same : You now have 2 options
    2.1) Remove Left Element and Check for the Rest of String OR
    2.2) Remove Right Element and Check for the Rest of the string.
    If either of them dont give palindrome then its not a palindorme.*/
    
    public boolean validPalindrome(String s) {
        
        if(s == null || s.length() == 0) {
            return false;
        }
        
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return isPalindromic(s, start+1, end) || isPalindromic(s, start, end-1);
            }
            start++;
            end--;
        }   
        return true;
    }
    
    public boolean isPalindromic(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
