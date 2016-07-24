/*Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, 
which are ordered in lexicographical order. The index begins at 1.

Example

Given [1,2,4], return 1.
*/

public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */
    public long permutationIndex(int[] A) {
        // Write your code here
        long sum = 1; //这个1就是4*5! + 0*4! + 2*3! + 1*2! + 0*1! + 1中的最后的那个1, 在最开始直接初始化它
        for (int i = 0; i < A.length; i++){
            long temp1 = factorial(A.length - i - 1);
            int temp2 = post(A, i);
            sum += temp1 * temp2;
        }
        return sum;
    }
    
    private static long factorial(int n) { //阶乘用long
		long sum = 1;
		while(n > 0) {
			sum *= (n--);
		}
		return sum;
	}
	
	private static int post(int[] array, int index) {
		int temp = array[index];
		int count = 0;
		for(int i = index; i < array.length; i++) { //从传过来的index开始找index后面比array[index]小的
			if(array[i] < temp) {
				count++;
			}
		}
		return count;
	}
	
	/*
	testCase: 5, 1, 4, 3, 2, 8
	算式: 4*5! + 0*4! + 2*3! + 1*2! + 0*1! + 1
	分析: 找5开头前面有多少个, 先找5后面比5小的数的个数(1,4,3,2), 然后乘以(1,4,3,2)每个数作为开头所能组成的个数(就是阶乘), 然后再找以51开头的(1后面有多个比它小的), 然后是514开头的(就是4后面比它小的数的个数), 以此类推
	*/
}
