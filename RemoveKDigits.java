/*Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest 
possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.*/

class Solution {

    //solution1: O(n)时间, O(n)空间, 一个栈解法
    public String removeKdigits(String num, int k) {
        
        if(num == null || num.length() == 0 || k > num.length()) {
            return "";
        }
        
        //corner case
        if(k == num.length()) { //num=10, k=2适用于这个判断
            return "0";
        }      
            
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while(i < num.length()){
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        
        // corner case like "1111";
        while(k > 0){
            stack.pop();
            k--;            
        }
        
        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        
        //remove all the 0 at the head
        while(sb.length()>1 && sb.charAt(0)=='0') { //num=100, k=2适用于这个判断; 还有上面的num=10200, k=1也适用
            sb.deleteCharAt(0); //StringBuilder没有remove方法, 删除就是deleteCharAt(index)
        }
        return sb.toString();
    }
}
