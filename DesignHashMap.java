/*Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.*/

class MyHashMap {

    private static final double LOAD_FACTOR = 0.75;
    private Node[] nodes;
    private int size; // number of keys
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        nodes = new Node[5];
        size = 0;
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int idx = hash(key);
        for (Node x = nodes[idx]; x != null; x = x.next) { //从链表头往下找
            if (x.key == key) {
                x.value = value;
                return;
            }
        }
        nodes[idx] = new Node(key, value, nodes[idx]); //每一次在链表头插入新元素, 把其他元素往下挤, 
        //这句翻译过来就是: new Node的下一个节点是现在的链表头nodes[idx], 然后把new Node的地址作为新的链表头
        size++;
        
        double loadFactor = (double) size / nodes.length;
        if (loadFactor > LOAD_FACTOR) {
            rehash();
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = hash(key);
        for (Node x = nodes[idx]; x != null; x = x.next) {
            if (x.key == key) {
                return x.value;
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = hash(key);
        Node pre = new Node(-1, -1, nodes[idx]); // sentinal node before list head, 虚拟节点
        for (Node prev = pre; prev.next != null; prev = prev.next) {
            if (prev.next.key == key) {
                prev.next = prev.next.next;
                size--;
                break;
            }
        }
        nodes[idx] = pre.next;
    }
    
    private int hash(int key) {
        return key % nodes.length;
    }
    
    private void rehash() {
        Node[] tmp = nodes;
        nodes = new Node[tmp.length * 2];
        size = 0;
        for (Node head : tmp) { //遍历表头
            for (Node x = head; x != null; x = x.next) { //遍历每个链表
                put(x.key, x.value);
            }
        }
    }
    
    class Node {
        int key;
        int value;
        Node next;

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
