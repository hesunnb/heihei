/*Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

Example

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.*/

public class Solution {
    /**
     * @param s A string
     * @return whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // Write your code here
        
        if(s == null || s.length() == 0) {
            return false;
        }
        
	Stack<Character> sk = new Stack<Character>();
	for(int i = 0; i < s.length(); i++) {
	  if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
		sk.push(s.charAt(i));
	  } else {
	    if(sk.isEmpty()) {
	        return false;
	    } else {
	        char temp = sk.pop();
    		if(s.charAt(i) == ')' && temp == '(' || s.charAt(i) == ']' && temp == '[' || s.charAt(i) == '}' && temp == '{') {
    			continue;
    		} else {
    			return false;
    		}
    		//if (Math.abs(s.charAt(i) - temp) > 2) return false; 上面的if,else可以用这个代替, ()对应ASCII为40,41; []是91,93; 
    		//{}是123,125; 所以用>2来判断
	    }
	  }
	}
	return sk.isEmpty();
    }
    
    
    //稍微简洁点
    public boolean isValid(String s) {
        
        Stack<Integer> p = new Stack<Integer>();
        for(int i = 0; i < s.length(); i++) {
            int q = "(){}[]".indexOf(s.substring(i, i + 1)); //得到s里面括号对应在q的下标
            if(q % 2 == 1) { //如果是右括号
                if(p.isEmpty() || p.pop() != q - 1) {
                  return false;
                }
            } else {
              p.push(q); //左括号就放入栈
            }
        }
        return p.isEmpty();
    }
}
