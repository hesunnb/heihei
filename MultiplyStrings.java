/*Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a 
string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.*/

class Solution {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return "0";
        }
        
        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int index1 = i + j;
                int index2 = i + j + 1;
                int sum = product + pos[index2]; //把上一步结果的p2处的值加上, 比如这一步算出来09, 上一步p2处的值是5, 如果把09分开直接把9加在p2
                //的5上, 得到的14是不能直接放在p2处的, 所以要先加再拆分
                pos[index1] += sum / 10; //p1处的值累加, 99*99的例子可以很好说明p1的累加, p2每次对应的都是个位, p1是十位, 所以p2要先加上
                //产生进位, p1处就算加完十位变得>10, 比如17, 也是暂时的, 因为下一次计算也一定会被拆开
                pos[index2] = sum % 10; //p2处的值放置替换, 因为p2处的值已经加过了, 再做的拆分, 所以p2处的值直接替换
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i : pos) {
            if(!(sb.length() == 0 && i == 0)) { //去掉pos数组中的leading zero, 当sb中没有值的时候p还是0, p就是leading zero
                sb.append(i);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
