/*Given an unsorted integer array, find the first missing positive integer.

Example
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.*/

public class Solution {
    /**    
     * @param A: an array of integers
     * @return: an integer
     */
    //1放在0号位置, 2放在1号位置, 每个整数放在它应该在的相应位置
	public int firstMissingPositive(int[] A) {
        if (A == null) {
            return 1;
        }

        for (int i = 0; i < A.length; i++) {
            while (A[i] > 0 && A[i] <= A.length && A[i] != (i+1)) {
            //负数不去找它的相应位置, 就是用来被别人换来换去的, 超过A.length的数也不去找, 其余的情况的数都去找其相应位置
                int tmp = A[A[i]-1]; //三步交换
                if (tmp == A[i]) { //对于1,1这种输入, 如果不跳出的话就会死循环
                    break;
                }
                A[A[i]-1] = A[i];
                A[i] = tmp;
            }
        }

        for (int i = 0; i < A.length; i ++) { //从头到尾扫, 第一个不在相应位置的数就是应该返回的正数
                if (A[i] != i + 1) {
                    return i + 1;
                }
        }

        return A.length + 1; //这个情况是大家都在自己该在的位置的时候, 比如{1,2,3}
    }
	/*testCase:
	9,7,5,3 返回1
	9,7,5,4,4,3,2,1 返回6
	1,2,3 返回4
	1,1 返回2
	*/
}
