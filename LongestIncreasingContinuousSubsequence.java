/*
Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:

    Can be from right to left or from left to right.
    Indices of the integers in the subsequence should be continuous.

Notice
O(n) time and O(1) extra space.

Example

For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
*/

public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
     
    //从头到尾扫一遍(Maximum Subarray的想法)
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // Write your code here
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int longestHere = 1;
        int longestSoFar = 1;
        int i = 0;
        while(i < A.length - 1) {
            while(i < A.length - 1 && A[i] > A[i + 1]) {
                longestHere++;
                i++;
            }
            longestSoFar = Math.max(longestSoFar, longestHere);
            longestHere = 1;
            while(i < A.length - 1 && A[i] < A[i + 1]) {
                longestHere++;
                i++;
            }
            longestSoFar = Math.max(longestSoFar, longestHere);
            longestHere = 1;
        }
        return longestSoFar;
    }
    
    
    //从头到位, 从尾到头, 按照一个顺序扫两遍
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int answer = 1;
        
        // from left to right
        int length = 1; // just A[0] itself
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }
        
        // from right to left
        length = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }
        
        return answer;
    }
}
