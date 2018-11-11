/*Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]*/

public class Solution {
    public List<List<String>> partition(String s) {
        
        List<List<String>> result = new ArrayList<List<String>>();
        if(s == null || s.length() == 0) {
            return result;
        }
        
        List<String> path = new ArrayList<String>();
        partitionHelper(result, path, s, 0);
        return result;
    }
    
    private void partitionHelper(List<List<String>> result, List<String> path, String s, int pos) {
        if(pos == s.length()) { //pos如果能到s.length()这个位置就说明回文一定存在了, 因为只有满足回文最后一个等于s.length()的i才能传进来
            result.add(new ArrayList<String>(path));
        }
        
        for(int i = pos + 1; i <= s.length(); i++) { //s中每一个字符的位置都保留一个pos, 然后每个字符都会从自己这个pos位置出发,
            //去判断从pos到i之间的字符串是不是回文, 一旦找到回文就加入, 然后把i传给下一个递归, 下一个递归把传进来的i当做自己的pos,
            //然后在这个pos的基础上继续从pos到i找回文, 回溯的时候, 把元素删掉后, i++, 就会截取新的字符串进行判断, 因为每个字符串都是
            //从自己的pos位置出发, 所以没有重复判断的情况
            if(!isPalindrome(s, pos, i - 1)) { //回文要pos到i - 1
                continue;
            }
            
            path.add(s.substring(pos, i)); //子串要pos到i
            partitionHelper(result, path, s, i); //传的也是i
            path.remove(path.size() - 1);
        }
    }
    
    private boolean isPalindrome(String s, int start, int end) { //简化版的判断, 这道题默认就是没有空格, 没有","这些字符串, 
        //比validPalindrome要简化
        while(start < end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    /*testCase: "aab", "abba", "abbcbba", "abbcca"
    "aab":
    [a, a, b]
    [aa, b]
    
    "abba":
    [a, b, b, a]
    [a, bb, a]
    [abba]
    
    "abbcbba":
    [a, b, b, c, b, b, a]
    [a, b, b, c, bb, a]
    [a, b, bcb, b, a]
    [a, bb, c, b, b, a]
    [a, bb, c, bb, a]
    [a, bbcbb, a]
    [abbcbba]
    
    "abbcca":
    [a, b, b, c, c, a]
    [a, b, b, cc, a]
    [a, bb, c, c, a]
    [a, bb, cc, a]
    */
}
