/*Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most 
k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.*/

class Solution {
    
    //testcase: XXXYZXXAXXX, k = 1
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int[] count = new int[26];
        int start = 0;
        int end = 0;
        int maxCount = 0;
        int maxLength = 0;
        while(end < s.length()) {
            count[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(end) - 'A']);
            while(end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }
    /*完美解释:
    In case anyone is confused by this solution, here's another way of explaining it:

    end-start+1 = size of the current window
    maxCount = largest count of a single, unique character in the current window

    The main equation is: end-start+1-maxCount

    When end-start+1-maxCount == 0, then then the window is filled with only one character
    When end-start+1-maxCount > 0, then we have characters in the window that are NOT the character that occurs the most. 
    end-start+1-maxCount is equal to exactly the # of characters that are NOT the character that occurs the most in that window. 
    Example: For a window "xxxyz", end-start+1-maxCount would equal 2. (maxCount is 3 and there are 2 characters here, "y" and "z" 
    that are not "x" in the window.)

    We are allowed to have at most k replacements in the window, so when end-start+1-maxCount > k, then there are more characters 
    in the window than we can replace, and we need to shrink the window.

    If we have window with "xxxy" and k = 1, that's fine because end-start+1-maxCount = 1, which is not > k. maxLength gets updated 
    to 4.

    But if we then find a "z" after, like "xxxyz", then we need to shrink the window because now end-start+1-maxCount = 2, and 2 > 1. 
    The window becomes "xxyz".

    maxCount may be invalid at some points, but this doesn't matter, because it was valid earlier in the string, and all that matters 
    is finding the max window that occurred anywhere in the string. Additionally, it will expand if and only if enough repeating 
    characters appear in the window to make it expand. So whenever it expands, it's a valid expansion.

    Hope that helps.

    P.S. Yes, as several other people mentioned already, the while should be replaced with if. The time complexity is exactly 
    the same, because the while-loop only runs once anyway.
    */
}
