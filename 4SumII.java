/*Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is 
zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 
and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0*/

class Solution {
    /*Time complexity:  O(n^2)
    Space complexity: O(n^2), 空间最坏情况是O(n^2), 每个sum都是unique的*/
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        
        if(A == null || B == null || C == null || D == null || A.length == 0 || B.length == 0 || C.length == 0 || D.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < C.length; i++) {
            for(int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1); //把两组所能组成的和放入map中
            }
        }

        int result=0;
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                result += map.getOrDefault(-1 * (A[i]+B[j]), 0); //按照新组合的相反值在map中查找, 找到了就说明能够加和为0, 正好把有多少个
                //这样的sum给取出来加到result中
            }
        }

        return result;
    }
}
