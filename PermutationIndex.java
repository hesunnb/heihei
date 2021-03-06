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
     
    //用哈希表, O(n^2)时间, O(n)空间
    //详细解释见permutationIndex2
    //思路: 目的就是求比A中所表示的排列小的排列有多少个, 这样就知道A中排列的位置了
    public long permutationIndex(int[] A) {
        // Write your code here
        
        if(A == null || A.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i = 0; i < A.length; i++) { //数组存到哈希表中
            if(m.containsKey(A[i])) {
                m.put(A[i], m.get(A[i]) + 1);
            } else {
                m.put(A[i], 1); //因为这道题A数组中没有重复, 所以个数都为1
            }
        }
        
        long result = 0;
        for(int i = 0; i < A.length; i++) {
            for(int j = i + 1; j < A.length; j++) {
                if(A[j] < A[i]) { //A[j]是用来每次作为开头的元素, 因为要统计比A中所代表的排列小的排列, 所以A[j](开头)所代表的值要比A[i]小
                    m.put(A[j], m.get(A[j]) - 1); //开头的元素不参与后面其它元素的全排列
                    result += generateNum(m); //产生全排列并计数
                    m.put(A[j], m.get(A[j]) + 1); //把本次作为开头的元素加回来, 用于其他时候的全排列(作为非开头元素的时候)
		}
            }
            m.put(A[i], m.get(A[i]) - 1); //本次i的开头都计算完了, 不再参与计算了
        }
        return result + 1; //要加上自己的位置
    }
    
    private long generateNum(Map<Integer, Integer> m) {
        int sum = 0;
        for(int val : m.values()) {
            if(val == 0) {
                continue; //等于0就是不参与计算, 作为开头的元素是不参与判断的, 因为上面的-1
            }
            sum += val; //sum就是在统计除了开头的元素之外, 还有多少个元素能够用来组成全排列
        }
        return factorial(sum); //因为A中没有重复, 所以不需要去重(PermutationIndex2), 直接算sum的阶乘即可
    }
    
    private long factorial(int num) {
        long sum = 1;
        while(num > 0) {
            sum *= (num--);
        }
        return sum;
    }
    /*testCase: 5, 1, 4, 3, 2, 8
    算式: 4*5! + 0*4! + 2*3! + 1*2! + 0*1! + 1
    分析: 找5开头前面有多少个, 先找5后面比5小的数的个数(1,4,3,2), 然后乘以(1,4,3,2)每个数作为开头所能组成的个数(就是阶乘), 
    然后再找以51开头的(1后面有多个比它小的), 然后是514开头的(就是4后面比它小的数的个数), 以此类推*/
}


public class Solution {

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
}
