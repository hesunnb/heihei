/*A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive 
elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.*/

class Solution {

    //solution1: 动归解法, 时间O(n)
    public int numberOfArithmeticSlices(int[] A) {
        
        if(A == null || A.length < 3) {
            return 0;
        }
        
        int curr = 0, sum = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1; //动归解法, cur就是算上A[i]这个数的时候, 有多少个Arithmetic Slices
                sum += curr; //sum存的是之前所有的Arithmetic Slices
            } else {
                curr = 0;
            }
        }
        return sum;
    }
    
    
    //solution2: (own), 时间O(n^2)
    public int numberOfArithmeticSlices(int[] A) {
        
        if(A == null || A.length < 3) {
            return 0;
        }
        
        int result = 0;
        for(int i = 0; i < A.length-2; i++) {
            int step = A[i+1] - A[i]; //间隔长度
            int count = 1;
            for(int j = i+1; j < A.length; j++) {
                if(A[j] - A[j-1] == step) {
                    count++;
                    if(count >= 3) { //3个以上的都计数
                        result++;
                    }
                } else {
                    break;
                }
            }
        }
        return result;
    }
}