/*Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate 
the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys 
that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2  capacity  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4*/

/*一些其它例子:
Input:
["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
[[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]
Output:
[null,null,null,2,1,2,null,null,3,2,-1,4]
Expected:
[null,null,null,2,1,2,null,null,-1,2,1,4]*/


//version1: 三个哈希表 + 一个LinkedHashSet版本, 也是最好的版本, 双端链表用LinkedHashSet代替了, 如果要用双端链表, 见version2
class LFUCache {

    HashMap<Integer, Integer> vals; // key, value
    HashMap<Integer, Integer> counts; // key, count
    HashMap<Integer, LinkedHashSet<Integer>> lists; // count, LinkedHashSet
    int capacity;
    int min = -1; // 当capacity满了删除元素的时候用到

    public LFUCache(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>()); // 初始化先把count为1的给一个LinkedHashSet
    }

    public int get(int key) {
        if (capacity <= 0) {
            return -1;
        }

        if (!vals.containsKey(key)) {
            return -1;
        }

        int count = counts.get(key); // 得到对应的count
        counts.put(key, count + 1);
        lists.get(count).remove(key); // 从lists中对应的count中删除
        if (count == min && lists.get(count).size() == 0) {
            min++; // 以题目给的例子就能看出count==min的作用, 不能只有lists.get(count).size() == 0的条件,
            // 有可能count==2的LinkedHashSet
            // 没有了, 但是此时count==1的LinkedHashSet还有元素, 所以min还只能是1, 不能++
        }

        if (!lists.containsKey(count + 1)) {
            lists.put(count + 1, new LinkedHashSet<>()); // 没有就新建
        }
        lists.get(count + 1).add(key); // 放到下一个更大的+1的count中
        return vals.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key); // 相同key就调用一下get方法, 调整一下元素的顺序即可
            return;
        }
        if (vals.size() == capacity) { // 这里用不着>=, 因为没有大于的可能性
            int evit = lists.get(min).iterator().next(); // 删除元素的时候, min代表了目前最少访问次数的元素, 那么对于相同访问次数的元素
            // 要采用LRUCache的策略进行删除, 所以就是删除第一个(最不长访问的元素)
            lists.get(min).remove(evit);
            vals.remove(evit); // counts也可以counts.remove(evit), 但是没必要, 因为vals已经删完了, 比如删除了(3,3), 下次再放入3的时候,
            // 3的count会被自动刷新为1, 就是覆盖了
        }
        vals.put(key, value);
        counts.put(key, 1); // count为1
        min = 1; // 表名count为1的LinkedHashSet目前有元素
        lists.get(1).add(key); // 向LinkedHashSet中添加元素
    }
}


//version2: 把LinkedHashSet改为双端链表
class LFUCache {

    HashMap<Integer, Node> vals; // key, node
    HashMap<Integer, Integer> counts; // key, count
    HashMap<Integer, DLList> lists; // count, 双端链表
    int capacity;
    int min = -1; // 当capacity满了删除元素的时候用到

    public LFUCache(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new DLList()); // 初始化先把count为1的给一个双端链表
    }

    public int get(int key) {
        if (capacity <= 0) {
            return -1;
        }

        if (!vals.containsKey(key)) {
            return -1;
        }

        int count = counts.get(key); // 得到对应的count
        counts.put(key, count + 1);
        lists.get(count).remove(vals.get(key)); // 从lists中对应的count中删除, 删除的是node
        if (count == min && lists.get(count).size() == 0) {
            min++;
        }

        if (!lists.containsKey(count + 1)) {
            lists.put(count + 1, new DLList()); // 没有就新建
        }
        lists.get(count + 1).add(vals.get(key)); // 放到下一个更大的+1的count中
        return vals.get(key).value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (vals.containsKey(key)) {
            vals.get(key).value = value;
            get(key); // 相同key就调用一下get方法, 调整一下元素的顺序即可
            return;
        }
        if (vals.size() == capacity) {
            int evit = lists.get(min).removeHead(); //移除头节点就符合了LRUCache的策略
            vals.remove(evit); //注意removeHead()返回的是node的key, 所以vals移除没有问题, 不能返回node的value
        }
        Node node = new Node(key, value);
        vals.put(key, node);
        counts.put(key, 1); // count为1
        min = 1; // 表名count为1的双端链表目前有元素
        lists.get(1).add(node); // 向双端链表中添加元素
    }
    
    class DLList {
        private Node head;
        private Node tail;
        private int size = 0; //用于做判断用, 判断双端链表有没有空
        
        DLList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }
        
        public void add(Node insert) {
            moveToTail(insert);
            size++;
        }
        
        public void remove(Node current) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
        
        public int removeHead() {
            int result = head.next.key;
            head.next = head.next.next; //从链表中移除第一个点
            head.next.prev = head;
            size--;
            return result;
        }
        
        public void moveToTail(Node current) { //向链表尾处插入节点
            current.prev = tail.prev;
            tail.prev = current;
            current.prev.next = current;
            current.next = tail;
        }
        
        public int size() {
            return this.size;
        }
    }
    
    class Node { //内部类
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
}
