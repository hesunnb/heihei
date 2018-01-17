/*Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty 
operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque 
(double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).


举例分析:
push 1 -> [1]       and no rotate
push 2 -> [2,1]     and rotate 1 time : -> [1,2]
push 3 -> [2,1,3]   and rotate 2 times: -> [1,3,2] -> [3,2,1]
push 4 -> [3,2,1,4] and rotate 3 times: -> [2,1,4,3] -> [1,4,3,2] -> [4,3,2,1]
pop    -> [3,2,1]   and return 4
pop    -> [2,1]     and return 3
push 5 -> [2,1,5]   and rotate 2 times: -> [1,5,2] -> [5,2,1]
pop    -> [2,1]     and return 5*/



class MyStack {

    Queue<Integer> queue = new LinkedList<>();
    
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        for(int i = 0; i < queue.size() - 1; i++) { //比如现在是3,2,1,4 需要窜3次
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
