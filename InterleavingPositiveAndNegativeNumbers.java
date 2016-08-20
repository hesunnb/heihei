/*Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

 Notice

You are not necessary to keep the original order of positive integers or negative integers.

Example
Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.*/

class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
     
    //排了个序, O(nlogn)in place, 开空间的方法见九章(O(n))
    public static void rerange(int[] A) {
        // write your code here
        
        if(A == null || A.length <= 2) {
            return;
        }
        
        Arrays.sort(A);
        int positiveCount = 0;
        int negativeCount = 0;
        for(int i = 0; i < A.length; i++) { //统计正负数个数
            if(A[i] > 0) {
                positiveCount++;
            } else if(A[i] < 0) {
                negativeCount++;
            }
        }
        
        int start = 0;
        int end = 0;
        int count = 0;
        if(positiveCount > negativeCount) { //正数多
            while(A[end] < 0) {
                end++; //end指向第一个正数
            }
            while(start < end) {
                if(count % 2 == 0) {
                    swap(A, start, end);
                    start++;
                    end++;
                    count++;
                }
                if(count % 2 != 0) {
                    start++;
                    count++;
                }
            }
        } else { //负数多以及正负数相等
        	while(A[end] < 0) {
                end++; //end指向第一个正数
            }
        	while(start < end && end < A.length) {
                if(count % 2 == 0) {
                	start++;
                    count++; 
                }
                if(count % 2 != 0) {
                	swap(A, start, end);
                    start++;
                    end++;
                    count++;
                }
            }
        }
   }
   
   private static void swap(int[] A, int m, int n) {
       int temp = A[m];
       A[m] = A[n];
       A[n] = temp;
   }
}
