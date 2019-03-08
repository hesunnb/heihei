/*S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. 
More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.*/

class Solution {

    public String customSortString(String S, String T) {
        if(S == null || T == null || S.length() == 0 || T.length() == 0) {
            return T;
        }
        
        int[] count = new int[26];
        StringBuilder sb = new StringBuilder();
        for(char c : T.toCharArray()) { //统计T中字符串出现的次数
            count[c - 'a']++;
        }
        
        for(char c : S.toCharArray()) { //按顺序遍历S, 依照S的顺序添加T中的字符
            while(count[c - 'a'] > 0) {
                sb.append(c);
                count[c - 'a']--;
            }
        }
        
        for(char c = 'a'; c <= 'z'; c++) { //添加S中没有的, 而T中有的字符
            while(count[c - 'a'] > 0) {
                sb.append(c);
                count[c - 'a']--;
            }
        }
        return sb.toString();
    }
}
