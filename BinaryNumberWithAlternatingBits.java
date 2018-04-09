/*Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.
Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.
Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.*/

class Solution {
    public boolean hasAlternatingBits(int n) {
        
        //0和1都算真
        if(n < 0) {
            return false;
        }
        
        String s = Integer.toBinaryString(n);
        char temp = s.charAt(0);
        for(int i = 1; i < s.length(); i++) {
            if(temp != s.charAt(i)) {
                temp = s.charAt(i);
            } else {
                return false;
            }
        }
        return true;
    }
}
