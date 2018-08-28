/*Design your implementation of the circular double-ended queue (deque).

Your implementation should support following operations:

MyCircularDeque(k): Constructor, set the size of the deque to be k.
insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
getRear(): Gets the last item from Deque. If the deque is empty, return -1.
isEmpty(): Checks whether Deque is empty or not. 
isFull(): Checks whether Deque is full or not.
 

Example:

MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);			// return true
circularDeque.insertLast(2);			// return true
circularDeque.insertFront(3);			// return true
circularDeque.insertFront(4);			// return false, the queue is full
circularDeque.getRear();  			// return 32
circularDeque.isFull();				// return true
circularDeque.deleteLast();			// return true
circularDeque.insertFront(4);			// return true
circularDeque.getFront();			// return 4
 

Note:

All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Deque library.*/


//solution1: 用ArrayList解
class MyCircularDeque {

    private int size;
    List<Integer> myCircularDequeList;
    
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.size = k;
        myCircularDequeList = new ArrayList<>(k);
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(!this.isFull()) {
            myCircularDequeList.add(0, value);
            return true;
        }
        return false;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(!this.isFull()) {
            myCircularDequeList.add(value);
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(!this.isEmpty()) {
            myCircularDequeList.remove(0);
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(!this.isEmpty()) {
            myCircularDequeList.remove(myCircularDequeList.size() - 1);
            return true;
        }
        return false;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if(!this.isEmpty()) {
            return myCircularDequeList.get(0);
        }
        return -1;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if(!this.isEmpty()) {
            return myCircularDequeList.get(myCircularDequeList.size() - 1);
        }
        return -1;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return myCircularDequeList.isEmpty();
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return myCircularDequeList.size() == size ? true : false;
    }
}


//solution2: 数组
class MyCircularDeque {

    private int[] buffer;
    private int front, rear, len;
    
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        buffer = new int[k];
        front = buffer.length;
        rear = -1;
        len = 0;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    //用一个例子来表名为什么insertFront要这么写: insertFront(5), deleteFront(), insertLast(3), insertFront(7)
    public boolean insertFront(int value) {
        if(!isFull()) {
            front = (front - 1 + buffer.length) % buffer.length; 
            buffer[front] = value;
            len++;
            return true;
        }
        return false;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(!isFull()) {
            rear = (rear + 1) % buffer.length;
            buffer[rear] = value;
            len++;
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (!isEmpty()) {
            front = (front + 1) % buffer.length; 
            len--;
            return true;
        }
        return false;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (!isEmpty()) {
            rear = (rear - 1 + buffer.length) % buffer.length; //insertFront(9), deleteLast()
            len--;
            return true;
        }
        return false;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : buffer[front % buffer.length]; //insertLast(1), getFront()
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : buffer[(rear + buffer.length) % buffer.length]; 
        //例子: insertFront(9), getRear(), 为啥要加buffer.length
        //k=3, insertLast(1), insertLast(2), insertFront(3), getRear()
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return len == buffer.length;
    }
}
/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
