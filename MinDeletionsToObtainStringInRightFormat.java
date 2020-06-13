/*We are given a string SS of length NN consisting only of letters A and/or B. Our goal is to obtain a string in the format A...AB...B. 
(all letters A occur before all letters B) by deleting some letters from SS. In particular, strings consisting only of letters A or 
only of letters B fit this format.

Write a function that, given a string SS, return the minimum number of letters that need to be deleted from SS in order to obtain a 
string in the above format.

Example
Example 1

Input: "BAAABAB"
Output: 2
Explanation: We can obtain "AAABB" by deleting the first occurrence of 'B' and the last occurrence of 'A'.
Example 2

Input: "BBABAA"
Output: 3
Explanation: We can delete all occurrences of 'A' or 'B'.
Example 3

Input: "AABBBB"
Output: 0
Explanation: We do not have to delete any letters, because the given string is already in the expected format.
Notice
NN is an integer within the range [1,100\,000][1,100000];
string SS consists only of the characters A and/or B.*/

public class Solution {
    
    /*For example take the string BAABAB, the partitions are as follows:

    | BAABAB -> BBB
    B | AABAB -> BB
    BA | ABAB -> ABB
    BAA | BAB -> AABB
    BAAB | AB -> AAB
    BAABA | B -> AAAB
    BAABAB | -> AAA
    
    Define f(i) = number of Bs up-to index i [exclusive]+ number As after index i [inclusive]
    This function is the number of deletion required to transform the string to A...AB...B where index i corresponds 
    to the partition before the first B.
    
    Re-stating the problem: Find f(k) s.t. f(k) <= f(i) for all indices i.*/
    // O(n), 这个更容易懂
    public int minDeletionsToObtainStringInRightFormat(String s) {
        // write your code here
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        
        int rightA = 0;
        int leftB = 0;
        for(int i = 0; i < s.length(); i++) { //右边的A和左边的B是要删掉的, 这里统计出所有右边的A
            if(s.charAt(i) == 'A') {
                rightA++;
            }
        }
        
        int result = rightA;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A') {
                rightA--;
            } else {
                leftB++;
            }
            result = Math.min(result, leftB + rightA); //rightA + leftB就是总共要切除的A和B, result就是最少要切除的量
        }
        return result;
    }
    
    // 这个是非常好的贪心了, O(n)
    private static int minDelete(String s) {
        int cntA = 0, cntB = 0, cntRemove = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                cntA++; // 记录A的个数
                if (cntB > cntRemove) { // cntRemove指的就应该是cntBRemove
                    cntRemove++;
                }
            } else {
                cntB++; // 记录B的个数
                if (cntA == 0) { // 有B没有A的时候
                    cntRemove++;
                }
            }
        }
        return Math.min(cntA, Math.min(cntB, cntRemove));
    }
}
