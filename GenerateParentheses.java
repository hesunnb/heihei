/*Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]*/

public class Solution {
    public List<String> generateParenthesis(int n) {
    
        List<String> result = new ArrayList<String>();
        if(n <= 0) {
            return result;
        }
        
        helper(result, "", n, n);
        return result;
    }
    
    private void helper(List<String> result, String path, int left, int right) {
        
        if(left == 0 && right == 0) {
            result.add(path);
            return;
        }
        
        if(left > 0) { //如果左括号剩的比0多, 就加入左括号
            helper(result, path + "(", left - 1, right);
        }
        if(right > 0 && left < right) { //回溯的时候如果右括号剩的比0度, 并且左括号剩的比右括号少
            helper(result, path + ")", left, right - 1);
        }
    }
}
