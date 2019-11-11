/*Description
Implement a stack. You can use any data structure inside a stack except stack itself to implement it.

Example
Example 1:

Input:
push(1)
pop()
push(2)
top()  // return 2
pop()
isEmpty() // return true
push(3)
isEmpty() // return false
Example 2:

Input:
isEmpty()*/

public class Stack {

    // 栈容量大小，初始值10
    public int size = 10;
    // 栈满时，增长的大小 and 栈允许空的大小(2*step)， 超出限制就缩小栈
    public int step = 5;
    // 初始栈顶
    public int top = -1;
    // 初始化栈
    public int[] stack = new int[size];

    /*
     * @param x: An integer
     * 
     * @return: nothing
     */
    public void push(int x) {
        // write your code here

        // 如果栈满， 则扩大栈
        if (top >= (stack.length - 1)) {

            // 保存老栈数据
            int[] old_stack = stack;
            // 申请新栈
            stack = new int[stack.length + step];
            // 老栈数据转新栈
            for (int i = 0; i <= top; i++) {

                stack[i] = old_stack[i];
            }
            // 老栈数据释放
            old_stack = null;
        }

        // 压栈
        stack[++top] = x;
    }

    /*
     * @return: nothing
     */
    public void pop() {
        // write your code here

        // 如果栈为空，抛异常
        if (true == isEmpty()) {

            throw new RuntimeException("empty stack");
        }

        top--;

        // 如果剩余空间空余太多，缩小栈空间
        if ((stack.length - top - 1) > 2 * step) {

            int[] old_stack = stack;
            int[] stack = new int[old_stack.length - step];
            for (int i = 0; i <= top; i++) {

                stack[i] = old_stack[i];
            }

            old_stack = null;
        }

    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here

        // 如果栈不为空，返回栈顶值
        if (false == isEmpty()) {

            return stack[top];
        }

        // 否则，抛异常
        throw new RuntimeException("empty stack");
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        // write your code here

        if (top < -1) {

            throw new RuntimeException("top should never less than -1");
        }

        if (-1 == top) {

            return true;

        } else {
            return false;
        }
    }
}
