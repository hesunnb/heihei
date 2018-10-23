/*A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Follow up:
Can you solve it without using extra space?*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
     
    //next, random没有初始化，默认就是null
    //第一种方法： 是用哈希表来解决random指针这个问题，存在表中就可以找到啦，普通链表只能next，随意指针不能通过next实现，
    //所以就用哈希表
    public RandomListNode copyRandomList(RandomListNode head) {
        // write your code here
        if(head == null) {
            return null;
        }
        
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        RandomListNode newNode = null;
        while(head != null) { //每个head节点要判断两次，一个自己在不在，一个自己的random指针指向的结点在不在
            if(map.containsKey(head)) { //判断自己在不在(head作为key)
                newNode = map.get(head); //在的话就把head对应的value取出来
            }
            else {
                newNode = new RandomListNode(head.label); //不在就建立一个新结点
                map.put(head, newNode);
            }
            
            pre.next = newNode; //链接结点
            if(head.random != null) {
                if(map.containsKey(head.random)) {
                    newNode.random = map.get(head.random); //存在就把新结点的随意指针指向head随意指针指向的对象
                }
                else {
                    //不存在就建立一个结点，并存入和head随意指针指向结点的值，并建立对应关系；
                    //因为这个点建立对应关系后就存在与哈希表中，以后找出来就是已经存在点，直接使用
                    newNode.random = new RandomListNode(head.random.label); 
                    map.put(head.random, newNode.random);
                }
            }
            pre = newNode;
            head = head.next;
        }
        return dummy.next;
    }
    
    //第二种方法： 不用哈希表(但是空间仍用啦O(n),并不是O(1))
    /*第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4  。 然后扫描过程中 先建立copy节点 1->1`->2->2`->3->3`->4->4`, 
    //然后第二遍copy的时候去建立边的copy， 拆分节点, 一边扫描一边拆成两个链表，这里用到两个dummy node。第一个链表变回  1->2->3 , 
    //然后第二变成 1`->2`->3`  */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) {
            return null;
        }
        copyNext(head);
        copyRandom(head);
        return split(head);
    }
    
    private void copyNext(RandomListNode head) { //每次传过来的是值, 值传递, 所以不会改变原本head的位置
        while(head != null) {
            RandomListNode temp = new RandomListNode(0);
            temp.label = head.label;
            temp.next = head.next;
            temp.random = head.random;
            head.next = temp;
            head = head.next.next;
        }
    }
    
    private void copyRandom(RandomListNode head) {
        while(head != null) {
            if(head.next.random != null) { //这里head.random也可以
                head.next.random = head.random.next;
                //copyNext拷贝出来了一份完整的链表, 1->2->3->4变成了1->1`->2->2`->3->3`, 不过现在1与1`, 2与2`, 
                //3与3`它们的random指针还是相同的, 指的还是1,2,3,4中的节点, 这个操作就是让1`,2`,3`,4`中的random
                //指针也指向1`,2`,3`,4`中的值
            }
            head = head.next.next;
        }
    }
    
    private RandomListNode split(RandomListNode head) {
        RandomListNode newNode = head.next;
        while(head != null) {
            RandomListNode temp = head.next; //把1->1`->2->2`->3->3`->4->4`进行拆分, head一组, newNode一组
            head.next = temp.next;
            head = head.next; //因为head的next改变了, 所以head是两个一跳
            if(temp.next != null) {
                temp.next = temp.next.next; //temp也是两个一跳
            }
        }
        return newNode;
    }
}
