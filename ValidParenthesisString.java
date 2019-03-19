/*Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. 
We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].*/

class Solution {
    /*1. How to check valid parenthesis w/ only ( and )? Easy. Count each char from left to right. When we see 
    (, count++; when we see ) count--; if count < 0, it is invalid () is more than (); At last, count should == 0.
    2. This problem added *. The easiest way is to try 3 possible ways when we see it. Return true if one of them is valid.*/
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }
    
    private boolean check(String s, int start, int count) {
        if (count < 0) {
            return false;
        }
        
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            else if (c == ')') {
                if (count <= 0) { //这句话一定要加, 比如")("这种例子, 最后count==0, 但是中途count已经小于0了, 不符合条件了, 所以要
                    return false; //中断返回假
                }
                count--;
            }
            else if (c == '*') {
                return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
            }
        }
        
        return count == 0;
    }
}
