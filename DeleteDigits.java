/*Given string A representative a positive integer which has N digits, remove any k digits of the number, the remaining digits are arranged according to the original order to become a new positive integer.

Find the smallest integer after remove k digits.

N <= 240 and k <= N

Example
Given an integer A = "178542", k = 4

return a string "12"*/

public class Solution {
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    public String DeleteDigits(String A, int k) {
        // write your code here
        
        if(A == null || A.length() == 0 || k > A.length() || k < 0) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder(A);
		boolean flag = true;
		while(k > 0) {
			if(sb.charAt(0) == '0') { //如果有开头是0的就把0给删掉
				sb.deleteCharAt(0);
				continue;
			}
			if(flag) { //如果字符串A中有倒序的
				for(int i = 0; i < sb.length() - 1; i++) {
					if(sb.charAt(i) > sb.charAt(i + 1)) {
						sb.deleteCharAt(i);
						k--;
						break;
					} else if(i == sb.length() - 2 && (sb.charAt(i) < sb.charAt(i + 1))) { //如果找到最后两个数还是正序
					//就说明整个A字符串都是正序, 那么就去执行下面的处理正序的方法
						flag = false;
						break;
					}
				}
			} else { //如果字符串A从头到尾都是正序
				while(k > 0) {
					sb.deleteCharAt(sb.length() - 1); //就从尾巴开始删
					k--;
				}
			}
			
		}
		
		return sb.toString();
    }
}
/*testCase:
"10009876091", 4 //要处理开头的0
"123456789", 3 //正序处理
*/

//九章, 处理有倒序的情况都一样, 处理正序的时候这个稍微麻烦一点(每次都要遍历到最后才能删除)
public String DeleteDigits(String A, int k) {
        // write your code here
        StringBuffer sb = new StringBuffer(A);
		int i, j;
		for (i = 0; i < k; i++) {
			for (j = 0; j < sb.length() - 1	&& sb.charAt(j) <= sb.charAt(j + 1); j++) {
				//如果是正序就一直往后走
			}
			sb.delete(j, j + 1); //倒序就删掉大的那个, 正序就删掉最后一个
		}
        while (sb.length() > 1 && sb.charAt(0)=='0') //出来之后统一处理开头0
            sb.delete(0,1);
		return sb.toString();
    }
