/*Design and implement a data structure for Least Recently Used (LRU最近最少使用的) cache. It should support the following operations: 
get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate 
the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 capacity  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4*/

//version1: //双向链表版, 自己写双向链表来维护
public class LRUCache {
    
    private class Node { //内部类
        Node prev;
        Node next;
        int key;
        int value;
        
        Node(int key, int value) {
            this.prev = null;
            this.next = null;
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private Map<Integer, Node> map = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);
    
    public LRUCache(int capacity) { //构造函数
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(capacity <= 0) {
            return -1;
        }
        
        if(!map.containsKey(key)) {
            return -1;
        }
        
        Node current = map.get(key); //因为操作了值为key的这个点, 所以要把它移向链表的最后, 首先先把这个点从链表分离
        current.prev.next = current.next;
        current.next.prev = current.prev;
        
        moveToTail(current);
        
        return map.get(key).value;
    }
    
    public void put(int key, int value) {
        if(capacity <= 0) {
            return;
        }
        
        //因为链表中的value永远都是正数, 所以用-1来代表无效
        if(get(key) != -1) { //说明链表中现在存在这个点, 存在这个点get函数就会返回这个点的value(为正数), 这就说明get函数被正常执行了
            //get函数正常执行就会把这个点moveToTail
            map.get(key).value = value; //把这个点moveToTail之后直接更改它的值就可以了
            return; //改完值马上返回
        }
        
        if(map.size() == capacity) { //要移除第一个节点
            map.remove(head.next.key); //从哈希表中移除第一个点的内容
            head.next = head.next.next; //从链表中移除第一个点
            head.next.prev = head;
        }
        
        Node insert = new Node(key, value); //说明链表没有这个节点, 就新建一个节点
        map.put(key, insert); //在哈希表中加入相关内容
        moveToTail(insert); //把这个新节点放到链表末尾
    }
    
    private void moveToTail(Node current) { //向链表尾处插入节点
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


//version2: LinkedHashSet简化版, 因为LinkedHashSet底层操作的就是双向链表, 所以把双向链表的操作部分用LinkedHashSet来实现
class LRUCache {

    //双向链表
    private class Node { //内部类
        int key;
        int value;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private Map<Integer, Node> map = new HashMap<Integer, Node>();
    private LinkedHashSet<Node> set = new LinkedHashSet<>();
    
    public LRUCache(int capacity) { //构造函数
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(capacity <= 0) {
            return -1;
        }
        
        if(!map.containsKey(key)) {
            return -1;
        }
        
        Node current = map.get(key); //因为操作了值为key的这个点, 所以要把它移向链表的最后, 首先先把这个点从链表分离
        set.remove(current); //从set中移除这个点
        set.add(current); //加到链表末尾
        
        return map.get(key).value;
    }
    
    public void put(int key, int value) {
        if(capacity <= 0) {
            return;
        }
        
        if(get(key) != -1) { //说明链表中现在存在这个点
            map.get(key).value = value;
            return; //改完值马上返回
        }
        
        if(map.size() == capacity) { //要移除第一个节点
        	Iterator<Node> ite = set.iterator();
        	Node removeNode = ite.next();
            map.remove(removeNode.key); //从哈希表中移除第一个点的内容
            set.remove(removeNode); //从set中移除头结点
        }
        
        Node insert = new Node(key, value); //说明链表没有这个节点, 就新建一个节点
        map.put(key, insert); //在哈希表中加入相关内容
        set.add(insert); //把这个新节点放到链表末尾
    }
}
