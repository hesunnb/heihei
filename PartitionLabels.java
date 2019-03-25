/*A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears 
in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.*/

class Solution {
    public List<Integer> partitionLabels(String S) {
        
        List<Integer> result = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return result;
        }
        
        int[] map = new int[26];
        for(int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i; //统计每个字符最后出现的位置
        }
        
        int begin = 0;
        int end = 0;
        for(int i = 0; i < S.length(); i++) {
            if(map[S.charAt(i) - 'a'] > end) {
                end = map[S.charAt(i) - 'a']; //end是能够包容当前子串的最后下标位置
            }
            if(i == end) { //i如果碰到最后下标位置, 说明这个位置可以包含之前的所有字母的最后位置, 截取长度即可
                result.add(end - begin + 1);
                begin = end + 1;
            }
        }
        return result;
    }
}
