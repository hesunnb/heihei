/* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome. */

public class Solution {
    public boolean isPalindrome(String s) {
        
        if(s == null || s.length() == 0) {
            return true;
        }
        
        int front = 0;
        int end = s.length() - 1;
        while(front < end) {
            while(front < s.length() && !isValid(s.charAt(front))) { //如果front要不是有效字符，就跳过
                front++; //直到碰到有效字符
            }
            if(front == s.length()) {
                return true; //对于像".,,,,"这种输入也是空字符串，都跳过了，相当于没有，这时也为真
            }
            while(end >= 0 && !isValid(s.charAt(end))) {
                end--; //直到碰到有效字符
            }
            if(Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end))) {
                break;
            } else {
                front++;
                end--;
            }
        }
        return end <= front; //这里要有等号，因为若是输入" ",这种开始的时候front就等于end，然后上面的都不执行就到了return这句，此时end和front是相等的
    }
    
    private boolean isValid(char c) {
        return Character.isLetterOrDigit(c);
    }
}
