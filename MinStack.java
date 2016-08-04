/* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

//two stack solution
public class MinStack {

    /** initialize your data structure here. */
    private Stack<Integer> sk;
    private Stack<Integer> minSk;
    public MinStack() {
        sk = new Stack<Integer>();
        minSk = new Stack<Integer>();
    }
    
    public void push(int x) {
        if(minSk.isEmpty() || x <= minSk.peek()) { //小于等于minSk的顶端就放进去
            minSk.push(x);
        }
        sk.push(x);
    }
    
    public void pop() {
        if(sk.peek().equals(minSk.peek())) { //这里用sk.peek() == minSk.peek()就不可以, 因为peek返回的是对象, 所以用==比较会出错
        //除非写成(int)sk.peek() == (int)minSk.peek()
            minSk.pop();
        }
        sk.pop();
    }
    
    public int top() {
        return sk.peek();
    }
    
    public int getMin() {
        return minSk.peek();
    }
}


//One stack Good Solution
public class MinStack {

    /** initialize your data structure here. */
    private Stack<Integer> sk;
    private int min;
    public MinStack() {
        sk = new Stack<Integer>();
        min = Integer.MAX_VALUE; //即使第一个输进来是Integer.MAX_VALUE, 也会等于min从而把备份老的min(Integer.MAX_VALUE)加入
    }
    
    public void push(int x) { //放入每个新的最小值的时候都带着上一个最小值的备份, 这样中间夹着大的值的时候最小值不会丢失
        if(x <= min) {//这里等于min也要放两个, 因为pop的时候只要peek等于min就会pop两个
            sk.push(min);
            min = x;
        }
        sk.push(x);
    }
    
    public void pop() { //如果pop会造成最小值改变的话, 就连续pop出来两个, 把之前备份加进去的那个最小值更新为新的最小值
        if(sk.peek() == min) { //peek()会返回Integer对象, 但是与min比较的时候会自动解包, 所以用==没问题
            sk.pop();
            min = sk.peek();
        }
        sk.pop();
    }
    
    public int top() {
        return sk.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
