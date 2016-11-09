/*Given an expression string array, return the final result of this expression

 Notice

The expression contains only integer, +, -, *, /, (, ).

Example
For the expression 2*6-(23+7)/(1+2),
input is

[
  "2", "*", "6", "-", "(",
  "23", "+", "7", ")", "/",
  (", "1", "+", "2", ")"
],
return 2
*/

public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        // write your code here
        //先把中缀表达式转换成逆波兰表达式
	//然后再求逆波兰表达式的值
	//两个小题的综合

        ArrayList<String> list = new ArrayList<String>();  
        Stack<String> stack = new Stack<String>();  
          
        for (int i = 0; i < expression.length; i++) {  
            String str = expression[i];  
            if (isOp(str)) { //如果要是运算符
                if (str.equals("(")) {  //左括号直接就入栈了, 单独处理了一下, 因为别的运算符涉及到优先级处理的问题, 所以在下面处理
                    stack.push(str);  
                } else if (str.equals(")")) {  //是右括号的话
                    while (!stack.isEmpty()) { //将栈里所有的符号都拿出来直到遇到左括号
                        String p = stack.pop(); 
                        if (p.equals("(")) {  //取出的是左括号就停止
                            break;  
                        }  
                        list.add(p);  //取出的符号都放入到list里面
                    }  
                } else {  //左右括号都不是的符号
                    while (!stack.isEmpty() && order(str) <= order(stack.peek())) {  //要入栈的运算符比栈顶运算符优先级高就直接入栈
                        list.add(stack.pop());  //低的话就把栈里面按顺序所有比str优先级高符号拿出来加入到list
                    }  
                    stack.push(str);  //再把这个优先级低的符号入栈
                }  
            } else {  //不是运算符, 是数字的话就直接加入到list中
                list.add(str);  
            }  
        }  
        while (!stack.isEmpty()) {  //把最后栈里面剩的符号都加入到list
            list.add(stack.pop());  
        }  
        
        if(list.isEmpty()) { //说明所给的expression数组是空的
            return 0; //结果就是0, 返回
        }
        
        String operators = "+-*/"; //题目说只有这4种运算符
        for(String str : list){
            if(!operators.contains(str)){ //不是运算符就入栈
                stack.push(str);
                continue;
            }

            int a = Integer.parseInt(stack.pop()); //是运算符就弹出两个数进行计算
            int b = Integer.parseInt(stack.pop());
            if(str.equals("+")) {
                stack.push(String.valueOf(b + a));
            } else if(str.equals("-")) { //后弹出来的数要放在前面, 计算完还要把计算的结果入栈
                stack.push(String.valueOf(b - a));
            } else if(str.equals("*")) {
                stack.push(String.valueOf(b * a));
            } else {
                stack.push(String.valueOf(b / a));
            }
        }
        
        return Integer.parseInt(stack.pop()); //最后栈中就剩一个结果, 返回就行
    }
    
    private boolean isOp(String str) {  
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")  
            || str.equals("(") || str.equals(")")) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    private int order(String a) {  
        if (a.equals("*") || a.equals("/")) {  //乘除优先级设为2
            return 2;  
        } else if (a.equals("+") || a.equals("-")) {  //加减优先级设为1
            return 1;  
        } else {  //左括号优先级设为0, 因为肯定有符号要放到左括号的顶上, 所以左括号也要参与比较优先级
            return 0;  
        }  
    }
};
