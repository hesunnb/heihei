/*Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //这道题用bfs行的原因是因为有next指针的原因, dummy.next每次指向树每一行的行首, 然后每一行用next指针进行遍历, 普通的bfs还是得用队列
    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        
        Node newRoot = root;
        while (newRoot != null) {
            Node dummy = new Node(0);
            Node current = dummy;
            while (newRoot != null) {
                if (newRoot.left != null) { 
                    current.next = newRoot.left; 
                    current = current.next; 
                }
                if (newRoot.right != null) { 
                    current.next = newRoot.right; 
                    current = current.next; 
                }
                newRoot = newRoot.next;
            }
            newRoot = dummy.next; //newRoot下窜到下一行行首
        }
        return root;
    }
    /*一个testcase, 注意9那个地方左右都没有子节点, 所以dfs要注意
                              2
                           /     \
                          1       3
                         / \     / \
                        0   7   9   1
                       /   / \     / \
                      2   1   0   8   8
                             / 
                            7
    */
    //dfs
    public void connect(TreeLinkNode root) {
        
        if(root == null) {
            return;
        }
        
        TreeLinkNode cur = root;
        if(cur.left != null) {
            cur.left.next = (cur.right != null) ? cur.right : getNext(cur);
        }
        if(cur.right != null) {
            cur.right.next = getNext(cur);
        }
        
        connect(root.right); //用根右左的方式来完成太机智了, 问题就是根左右的时候, 上面那个例子只能先连好0->7->9, 9和1之间根左右方式遍历的时候
        //连不上, 除非用下面那种while的方式(但是while的方式存在重复连接问题), 那么就用根右左解决, 先去右边连接, 连完了再去左边连接, 那么这时候
        //getNext向右边找node的时候, 之前右边已经连好的连接(next指针)就能用上了, 这样7->9->1已经连好, 然后0就可以找到后面的8了
        connect(root.left); 
    }
    
    private TreeLinkNode getNext(TreeLinkNode root) {
        TreeLinkNode node = root.next;
        while(node != null) {
            if(node.left != null) {
                return node.left;
            }
            if(node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }
    
  
    //仅供参考, while能work, 但存在重复连接
    public void connect(TreeLinkNode root) {
        
        if(root == null) {
            return;
        }
        
        TreeLinkNode cur = root;
        while(cur != null) { //遍历一行
            if(cur.left != null) {
                cur.left.next = (cur.right != null) ? cur.right : getNext(cur);
            }
            if(cur.right != null) {
                cur.right.next = getNext(cur);
            }
            cur = cur.next; //用dfs做了bfs的事情, 在dfs的时候每次root都向左走, 走到每行开头的时候就把这行都连接完了, 之后递归的时候就存在
            //重复连接的情况, 存在多余的连接步骤
        }
        connect(root.left);
        connect(root.right);
    }
    
    private TreeLinkNode getNext(TreeLinkNode root) {
        TreeLinkNode node = root.next;
        while(node != null) { //找到下一个应该连接的节点, 用node向后窜来找节点
            if(node.left != null) {
                return node.left;
            }
            if(node.right != null) {
                return node.right;
            }
            node = node.next;
        }
        return null;
    }
}
