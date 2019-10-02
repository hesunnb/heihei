/*You have an infinite number of stacks arranged in a row and numbered (left to right) from 0, each of the stacks has the same 
maximum capacity.

Implement the DinnerPlates class:

DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack, and returns -1 if all stacks
are empty.
int popAtStack(int index) returns the value at the top of the stack with the given index and removes it from that stack, and 
returns -1 if the stack with that given index is empty.
Example:

Input: 
["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
Output: 
[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]

Explanation: 
DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // The stacks are now:  2  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 2.  The stacks are now:     4
                                                       1  3  5
                                                       ﹈ ﹈ ﹈
D.push(20);        // The stacks are now: 20  4
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.push(21);        // The stacks are now: 20  4 21
                                           1  3  5
                                           ﹈ ﹈ ﹈
D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
                                                        1  3  5
                                                        ﹈ ﹈ ﹈
D.popAtStack(2);   // Returns 21.  The stacks are now:     4
                                                        1  3  5
                                                        ﹈ ﹈ ﹈ 
D.pop()            // Returns 5.  The stacks are now:      4
                                                        1  3 
                                                        ﹈ ﹈  
D.pop()            // Returns 4.  The stacks are now:   1  3 
                                                        ﹈ ﹈   
D.pop()            // Returns 3.  The stacks are now:   1 
                                                        ﹈   
D.pop()            // Returns 1.  There are no stacks.
D.pop()            // Returns -1.  There are still no stacks.
 

Constraints:

1 <= capacity <= 20000
1 <= val <= 20000
0 <= index <= 100000
At most 200000 calls will be made to push, pop, and popAtStack.*/

class DinnerPlates {

    /*Q: Why not use a PriorityQueue?
    A: Recall my invariant: the last stack in the List must be nonempty. So if the last stack gets pop()ed to emptiness, then I 
    need to remove it from both the List and TreeSet. The priorityQueue does not support removing the largest element in log(n) time. 
    The TreeSet does.*/
    List<Stack<Integer>> stacks = new ArrayList<>();
    TreeSet<Integer> unFullStack = new TreeSet<>(); //用来记录哪些栈是没有满的, TreeSet自动排序
    int capacity;

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }

    public void push(int val) {
        if (unFullStack.isEmpty()) { //空的话就加一个
            stacks.add(new Stack());
            unFullStack.add(stacks.size() - 1);
        }
        Stack<Integer> stack = stacks.get(unFullStack.first());
        stack.push(val);
        if (stack.size() == capacity) { //满了删掉
            unFullStack.pollFirst();
        }
    }

    public int pop() {
        return myPop(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        return myPop(index);
    }

    public int myPop(int index) { //统一由myPop管理, 这个不错, 实现了复用, 这是CrackBook推荐的
        if (index < 0 || index > stacks.size() - 1 || stacks.get(index).isEmpty()) {
            return -1;
        }
        Stack<Integer> stack = stacks.get(index);
        int result = stack.pop();
        unFullStack.add(index); //pop之后重复加入index是没问题的, 自动去重
        while (stacks.size() > 0 && stacks.get(stacks.size() - 1).isEmpty()) {
            stacks.remove(stacks.size() - 1); //这个同步很重要, 理解的关键, stacks要remove的是空的, 既然是空的自然也肯定在unFullStack里
            unFullStack.pollLast();
        }
        return result;
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
