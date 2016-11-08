/*Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6*/
  
public class Solution {
    public int evalRPN(String[] tokens) {
        //前提是输入的已经是一个有效的逆波兰表达式
        Stack<Integer> s = new Stack<Integer>();
        String operators = "+-*/"; //题目说只有这4种运算符
        
        for(String token : tokens){
            if(!operators.contains(token)){ //不是运算符就入栈
                s.push(Integer.valueOf(token));
                continue;
            }

            int a = s.pop(); //是运算符就弹出两个数进行计算
            int b = s.pop();
            if(token.equals("+")) {
                s.push(b + a);
            } else if(token.equals("-")) { //后弹出来的数要放在前面, 计算完还要把计算的结果入栈
                s.push(b - a);
            } else if(token.equals("*")) {
                s.push(b * a);
            } else {
                s.push(b / a);
            }
        }
        
        return s.pop(); //最后栈中就剩一个结果, 返回就行
    }
}
