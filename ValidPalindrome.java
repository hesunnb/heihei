/* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome. */

public class Solution {
    public boolean isPalindrome(String s) {
        // Write your code here
        if (s.isEmpty()) {
            return true;
        }
        int start = 0, end = s.length() - 1;

        while(start < end) {

            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if(!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start++;
                end--;
            }
        }

        return true;
    }
    
    private boolean isvalid(char c) //不让用Character.isLetterOrDigit就得自己写个函数
    {
        return Character.isLetter(c)||Character.isDigit(c);
    }
}


// Tricky solution
public class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); //正则表达式, 把所有不是A-Za-z0-9这些的字符都变成""
        if(s.equals("") || s.length() == 1) return true; //如果要是s变成""(原s里面都是,,,..之类的)或者s里面只有一个字母, 那就是true

        return new StringBuilder(s).reverse().toString().equals(s); //有多个字母的话就倒序然后和原字符串相比, 如果相等就是回文
    }
}
