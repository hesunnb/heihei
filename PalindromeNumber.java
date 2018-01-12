/*Determine whether an integer is a palindrome. Do this without extra space.*/

//solution1
class Solution {
    
    //负数不是回文数
    public boolean isPalindrome(int x) {
        
        String s = String.valueOf(x);
        
        if (s.isEmpty()) {
            return true;
        }
        int start = 0, end = s.length() - 1;

        while(start < end) {
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}
