/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
     
//方法1：好：两两merge（1和2,3和4,5和6...），然后把结果再两两merge，最后成为一个链表，复杂度还是nklogk(自下而上)
//       不好：merge two by two,取两个排好，再和第三个排，以此类推，复杂度是nk2; (1+...+k)n + kn -> nk2

/*leetcode里面用的lists数组，那么把数组拷贝到集合里就行
        List<ListNode> result = new ArrayList<ListNode>();
        for(int i = 0; i < lists.length; i++)
        {
            result.add(lists[i]);
        }*/
    public ListNode mergeKLists(List<ListNode> lists) //lists里面装的都是链表头
    {
        if(lists == null || lists.size() == 0)
        {
            return null;
        }
        
        while(lists.size() > 1)
        {
            List<ListNode> newlists = new ArrayList<ListNode>();
            for(int i = 0; i + 1 < lists.size(); i += 2)
            {
                ListNode mergedlist = merge(lists.get(i), lists.get(i + 1)); //奇数个的时候就会不小于list.size()然后退出
                newlists.add(mergedlist);
            }
            //有奇数个链表就把最后一个没有参与合并的加入到新的Arraylist里，在最后一次合并就会把它合并啦
            if(lists.size() % 2 == 1) 
            {
                newlists.add(lists.get(lists.size() - 1));
            }
            lists = newlists;
        }
        return lists.get(0); //最后合并成一个返回就行
    }
    
    private ListNode merge(ListNode l1, ListNode l2) //如果一个为空，返回另一个链表；如果两个都为空，就返回空啦
    {
        if(l1 == null)
        {
            return l2;
        }
        if(l2 == null)
        {
            return l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                lastNode.next = l1;
                l1 = l1.next;
            }
            else
            {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next; //lastNode是每次一定要窜的
        }
        
        if(l1 != null) //说明l2为空, lastNode在l2上
        {
            lastNode.next = l1; //把lastNode连到l1上
        }
        else //说明l1为空, lastNode在l1上
        {
            lastNode.next = l2; //把lastNode连到l2上
        }
        return dummy.next;
    }

//方法2：divide & conquer(自上而下),就是找中点，一半一半拆开分着做
//复杂度还是nklog(k),就是方法1反着的求解
    public ListNode mergeKLists(List<ListNode> lists)
    {
        if(lists == null || lists.size() == 0)
        {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }
    
    private ListNode mergeHelper(List<ListNode> lists, int start, int end)
    {
        if(start == end)
        {
            return lists.get(start);
        }
        int mid = start + (end - start) / 2; //递归分解, 要merge所有list, 先merge中点左边, 再merge中点右边, 
        //左右半边再分别各自切割, 然后就能分解到单个点, 然后单个点返回自己的值, 上一个接收的函数再把单个点两两合并
        (left一个点, right一个点, return merge就合并),偶数个直接两两合并，奇数个点的时候是两个合完再和另一个一个合并
        //然后left和right就是合好的链表继续回朔, 最后成为合成一个大链表
        ListNode left = mergeHelper(lists, start, mid); //start和end会随着每次的调用而变化，所以能求出每段的合并值
        ListNode right = mergeHelper(lists, mid + 1, end);   
        return merge(left, right);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) //如果一个为空，返回另一个链表；如果两个都为空，就返回空啦
    {
        if(l1 == null)
        {
            return l2;
        }
        if(l2 == null)
        {
            return l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while(l1 != null && l2 != null)
        {
            if(l1.val < l2.val)
            {
                lastNode.next = l1;
                l1 = l1.next;
            }
            else
            {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next; //lastNode是每次一定要窜的
        }
        
        if(l1 != null) //说明l2为空, lastNode在l2上
        {
            lastNode.next = l1; //把lastNode连到l1上
        }
        else //说明l1为空, lastNode在l1上
        {
            lastNode.next = l2; //把lastNode连到l2上
        }
        return dummy.next;
    }
    
//方法3：Heap的方法:(用comparator),思路就是把每个链表的头结点装入优先级队列然后根据比较器自动挑出最小的，取出来，然后从取出点的那个链表再加入一个结点继续比较
        //复杂度：nklog(k),k是lists的长度，n是每个链表的长度，comparator是用二分法来找位置然后加入值的，所以每加入一个值就是一个log(k),总共加入nk次
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        //List里面装的都是每个链表的头结点

        if(lists == null || lists.size() == 0) //leetcode中是(ListNode[] lists),就把list.size()全部换成lists.length就行啦
        {
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        for(int i = 0; i < lists.size(); i++) //加入所有链表的头结点
        {
            if(lists.get(i) != null)
            {
                heap.add(lists.get(i));
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while(!heap.isEmpty())
        {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if(head.next != null)
            {
                heap.add(head.next); //每次再加入一个点继续比较
            }
        }
        return dummy.next;
    }
    
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() //匿名类的写法
    {
        public int compare(ListNode left, ListNode right) //重写compare函数
        {
            return left.val - right.val; //comparator中左边减右边是从小到大排，右边减左边是从大到小排
        }
    }; //有个分号
    
    
     //ListNode是自己写的类，要是比较类的话就得用comparator,指定比较类中的什么数值，进而按照这个数值进行排序，如果不用comparator就会出现ClassCastException这个异常，叫类型转换错误，因为ListNode无法直接强转成comparator
     
    //Comparator本身就是一个类，要实现它的一个匿名类，目的是重写compare方法
    //匿名类是不能有名称的类，所以没办法引用它们。必须在创建时，作为new语句的一部分来声明它们。 这就要采用另一种形式的new语句，如下所示： new <类或接口> <类的主体> 这种形式的new语句声明一个新的匿名类，它对一个给定的类进行扩展，或者实现一个给定的接口。它还创建那个类的一个新实例，并把它作为语句的结果而返回。要扩展的类和要实现的接口是new语句的操作数，后跟匿名类的主体
}
