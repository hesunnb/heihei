/*Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

 Notice

You couldn't cut wood into float length.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge 
O(n log Len), where Len is the longest length of the wood.*/

public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        
        if(L == null || L.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }
        
        int start = 1; //最短的木头(不是L里面的, 就是道理上最短的木头, 不能有长度为0的木头)
        int end = max; //L里最长的木头
        while(start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if(count(L, mid) >= k) { //表示按照mid的长度能够切出要求的个数
                start = mid; //就让start向前窜
            } else {
                end = mid; //切不出来就表示mid的长度过大, 要让end前移缩短范围
            }
        }
        
        if(count(L, end) >= k) { //如果能按end切就按end切, 因为end比start大
            return end;
        } 
        
        if(count(L, start) >= k) { //end不能就按start切
            return start;
        }
        return 0; //两个都不能切出来就是没有了, 比如L=[232, 124, 456], k=1000, 这样就切不出来
    }
    
    private int count(int[] L, int mid) {
        int sum = 0;
        for(int i = 0; i < L.length; i++) {
            sum += L[i] / mid;
        }
        return sum;
    }
    
    /*testCase:
    L=[232, 124, 456], k=7, return 114.
    L = {2147483644,2147483645,2147483646,2147483647}, k = 4, return 2147483644
    */
}
