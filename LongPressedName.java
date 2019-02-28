/*Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the 
character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters 
(possibly none) being long pressed.

 

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.
 

Note:

name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.*/

class Solution {

    //自己写的用StringBuilder然后从前面delete字符串的方法没问题, 但是下面这个方法更好, 值得学习
    public boolean isLongPressedName(String name, String typed) {
        if(name == null || typed == null || name.length() > typed.length()) {
            return false;
        }
        
        /*"kikcxmvzi"
          "kkiikcxxmmvvzz"*/
        int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; j++) {
            if (i < m && name.charAt(i) == typed.charAt(j)) {
                i++;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }   
        }  
        return i == m;
    }
}
