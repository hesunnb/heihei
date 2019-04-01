/*Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different 
integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

 

Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 

Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length*/

class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        if(A == null || A.length == 0 || K < 0) {
            return 0;
        }
        return kMost(A, K) - kMost(A, K - 1);
    }
    
    public int kMost(int[] A, int k) {
        
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int total = 0;
        int count = 0;
        while(end < A.length) {
            map.put(A[end], map.getOrDefault(A[end], 0) + 1);
            if(map.get(A[end]) == 1) {
                count++;
            }
            end++;
            while(count > k) {
                map.put(A[start], map.get(A[start]) - 1);
                if(map.get(A[start]) == 0) {
                    count--;
                }
                start++;
            }
            total+=end - start;
        }
        return total;
    }
}
