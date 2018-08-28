/*Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed 
based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also 
called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the 
queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, 
we can use the space to store new values.

Your implementation should support following operations:

MyCircularQueue(k): Constructor, set the size of the queue to be k.
Front: Get the front item from the queue. If the queue is empty, return -1.
Rear: Get the last item from the queue. If the queue is empty, return -1.
enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
isEmpty(): Checks whether the circular queue is empty or not.
isFull(): Checks whether the circular queue is full or not.
 

Example:

MyCircularQueue circularQueue = new MycircularQueue(3); // set the size to be 3
circularQueue.enQueue(1);  // return true
circularQueue.enQueue(2);  // return true
circularQueue.enQueue(3);  // return true
circularQueue.enQueue(4);  // return false, the queue is full
circularQueue.Rear();  // return 3
circularQueue.isFull();  // return true
circularQueue.deQueue();  // return true
circularQueue.enQueue(4);  // return true
circularQueue.Rear();  // return 4
 
Note:

All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Queue library.*/

/*设顺序存储队列用一维数组q[m]表示, 其中m为队列中元素个数, 队列中元素在向量中的下标从0到m-1.  
设队头指针为front, 队尾指针是rear, 约定front指向队头元素的前一位置, rear指向队尾元素. 当front等于-1时队空, rear等于m-1时为队满.
由于队列的性质("删除"在队头而"插入"在队尾), 所以当队尾指针rear等于m-1时, 若front不等于-1, 则队列中仍有空闲单元, 所以队列并不是真满.
这时若再有入队操作, 会造成假"溢出".*/


//solution1: 用ArrayList有点作弊, 没有体现出为了循环队列计算下标的那个特点, 如果要用数组实现, 就用solution2
class MyCircularQueue {

    private int size;
    List<Integer> myCircularQueueList;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.size = k;
        myCircularQueueList = new ArrayList<>(k);
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(!this.isFull()) {
            return myCircularQueueList.add(value);
        }
        return false;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(!this.isEmpty()) {
            myCircularQueueList.remove(0);
            return true;
        }
        return false;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if(!this.isEmpty()) {
            return myCircularQueueList.get(0);
        }
        return -1;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if(!this.isEmpty()) {
            return myCircularQueueList.get(myCircularQueueList.size() - 1);
        }
        return -1;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return myCircularQueueList.isEmpty();
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return myCircularQueueList.size() == size ? true : false;
    }
}


//solution2: 用数组实现
class MyCircularQueue {

    private int[] buffer;
    private int front, rear, len;
    private int size;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        buffer = new int[k];
        front = 0;
        rear = 0;
        size = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            buffer[rear] = value;
            rear = (rear + 1) % size; //让rear也从0开始, 与Design circular Deque那道题保持一致, 方便记忆
            len++;
            return true;
        }
        return false;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % size; //front起始位置是0
            len--;
            return true;
        }
        return false;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : buffer[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : buffer[(rear - 1 + size) % size];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() { //通过len来判断满没满
        return len == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == size;
    }
}
/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
