public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
        // write your code here
        
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
        return list;
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
}
