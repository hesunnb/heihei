/*
As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

 Example

push(1)
pop()     // return 1
push(2)
push(3)
top()     // return 2
pop()     // return 2

Challenge

implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.
*/

public class Queue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Queue() {
       // do initialization if necessary
       stack1 = new Stack<Integer>(); //stack1用来倒顺序, 然后执行相应操作返回结果
       stack2 = new Stack<Integer>(); //stack2用来push元素, 就是装元素
    }

    public void push(int element) {
        // write your code here
        stack2.push(element); //stack2用来容纳新进来的元素
    }

    public int pop() {
        // write your code here
        while(stack1.isEmpty()) { //stack1中没有元素就从stack2拿
            stack2ToStack1();
        }
        return stack1.pop(); //stack1返回结果
    }

    public int top() {
        // write your code here
        while(stack1.isEmpty()) {
            stack2ToStack1();
        }
        return stack1.peek();
    }
 
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
    
    private void stack2ToStack1() {
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop()); //把stack2的元素倒个顺序放到stack1中
        }
    }
}
