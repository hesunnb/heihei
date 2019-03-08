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

    //(own)
    public String customSortString(String S, String T) {
        if(S == null || T == null) {
            return null;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), map.getOrDefault(T.charAt(i), 0) + 1);
        }
        StringBuilder ssb = new StringBuilder(S);
        for(int i = 0; i < T.length(); i++) {
            if(!S.contains(String.valueOf(T.charAt(i)))) {
                ssb.append(T.charAt(i));
            } else {
                if(map.get(T.charAt(i)) > 1) {
                    int count = map.get(T.charAt(i));
                    while(count - 1 > 0) {
                        ssb.insert(ssb.indexOf(String.valueOf(T.charAt(i))), T.charAt(i));
                        count--;
                    }
                    map.put(T.charAt(i), 1);
                }
            }
        }
        
        int j = 0;
        while(j < ssb.length()) {
            if(!T.contains(String.valueOf(ssb.charAt(j)))) {
                ssb.deleteCharAt(j);
            } else {
                j++;
            }
        }
        return ssb.toString();
    }
}
