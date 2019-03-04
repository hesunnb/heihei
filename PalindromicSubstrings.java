/*Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.*/

class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            ////计算奇数子字符串
            result += helper(s, i, 0);
            //计算偶数子字符串
            result += helper(s, i, 1);
        }
        return result;
    }
    
    private int helper(String s, int index, int offset) {

        int left = index;
        int right = left + offset;
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; //因为只要是回文串就加入, 所以不判断回文串的重复与否, 有就count++
            left--;
            right++;
        }
        return count;
    }
    
    
    //这个是去重的版本, Find all distinct palindromic substrings
    public List<String> findAllPal (String str) {

        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return result;
        }

        for (int i = 0; i < str.length(); i++) {
            helper(str, i, 0, result);
            helper(str, i, 1, result);
        }
        return result;
    }
    
    private void helper (String str, int index, int diff, List<String> result) {

        int left = index;
        int right = index + diff;
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            String temp = str.substring(left, right + 1);
            if (!result.contains(temp)) { //在这里判断重复, 就看result当中有没有加过就行了
                result.add(temp);
            }
            
            left--;
            right++;
        }
    }
}
