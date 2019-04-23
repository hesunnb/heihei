/*Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  
Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.*/

class Solution {
    public List<String> letterCasePermutation(String S) {
        
        List<String> result = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return result;
        }
        
        helper(result, S, new StringBuilder(S), 0);
        return result;
    }
    
    public void helper(List<String> result, String S, StringBuilder sb, int pos) {
        if(pos == S.length()) {
            result.add(sb.toString());
            return;
        }
        
        if(Character.isDigit(S.charAt(pos))) { //数字直接跳过去
            helper(result, S, sb, pos + 1);
            return;
        }
        
        sb.setCharAt(pos, Character.toLowerCase(S.charAt(pos))); //在这里统一改小写
        helper(result, S, sb, pos + 1);
        
        sb.setCharAt(pos, Character.toUpperCase(S.charAt(pos))); //统一改大写, 不能写成如果是小写就改大写, 如果是大写就改小写, 这么写的话就会
        //改来改去还得去重, TLE
        helper(result, S, sb, pos + 1);
    }
}
