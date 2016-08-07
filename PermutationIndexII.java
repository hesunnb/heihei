/*Given a permutation which may contain repeated numbers, find its index in all the permutations of these numbers, 
which are ordered in lexicographical order. The index begins at 1.

Example

Given the permutation [1, 4, 2, 2], return 3.
*/

public class Solution {
    /**
     * @param A an integer array
     * @return a long integer
     */

    //用哈希表, O(n^2)时间, O(n)空间
	public long permutationIndexII(int[] A) {
		
		if(A == null || A.length == 0) {
		    return 0;
		}
		
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for(int i = 0; i < A.length; i++) { //把数组存到哈希表
		    if(m.containsKey(A[i])) {
		        m.put(A[i], m.get(A[i]) + 1);
		    } else {
		        m.put(A[i], 1);
		    }
		}
		
		long result = 0;
		for(int i = 0; i < A.length; i++) {
		    Map<Integer, Integer> flag = new HashMap<Integer, Integer>();
		    for(int j = i + 1; j < A.length; j++) {
		        if(A[j] >= A[i] || flag.containsKey(A[j])) { //后面的值比要判断的位大于等于, 或者flag中包含了重复的值
		            continue; //因为每位要判断的位需要是不用的值, 重复的值已经判断过了, 所以都要跳过
		        } else {
		            flag.put(A[j], 1);
		            m.put(A[j], m.get(A[j]) - 1); //比如5,3,3,4,2,2 判断5的时候, 5在哈希表中为0, 即5不参与全排列过程, 排除自己, 3也是一样, 要排除自己那个3, 剩余的3参与全排列过程
		            result += generateNum(m);
		            m.put(A[j], m.get(A[j]) + 1); //判断完要加回来
		        }
		    }
		    m.put(A[i], m.get(A[i]) - 1); //对于i每判断完一位要除掉一位
		}
		return result + 1; //要把自己所在的位置加上, 之前求的都是在它之前有多少个情况
	}
	
	private long generateNum(Map<Integer, Integer> m) {
	    long denominator = 1;
	    int sum = 0;
	    for(int val : m.values()) { //此处要学习, 因为我只需要从m.values()返回的Collection里面取值, 所以直接用增强for循环就行了
	        if(val == 0) {
	            continue;
	        }
	        denominator *= factorial(val);
	        sum += val;
	    }
	    /*Collection c = m.values(); //不用增强for就得这样写
	    for(Iterator i = c.iterator(); i.hasNext();) {
	        int val = (int)i.next();
	        if(val == 0) {
	            continue;
	        }
	        denominator *= factorial(val);
	        sum += val;
	    }*/
	    return factorial(sum) / denominator; //比如5!/3!1!1!, 第一次求除5以外所有人的全排列, 但是其余的值还有重复的, 所以还要除以它们的全排列
	}
	
	private long factorial(int n) { //阶乘用long
		long sum = 1;
		while(n > 0) {
			sum *= (n--);
		}
		return sum;
	}
}
