/*Determine whether an integer is a palindrome. Do this without extra space.*/

//solution2
class Solution {
    
    //负数不是回文数
    public boolean isPalindrome(int x) {
        
        String s = String.valueOf(x);
        
        if (s.isEmpty()) {
            return true;
        }
        int start = 0, end = s.length() - 1;

        while(start < end) { //判断起来比ValidPalindrome那道题还简单, 直接转换成字符串从头到尾走一遍就行, 但是在这里新建一个字符串都算extra space
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}
