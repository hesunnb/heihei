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
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
Example:

Given the following perfect binary tree,

     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
   
    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        
        return connectHelper(root);
    }
    
    public Node connectHelper(Node root) {
        if(root == null) {
            return null;
        }
        
        if(root.left != null) {
            root.left.next = root.right; //连接一个根的左右子树节点
        }
        
        if(root.right != null) {
            if(root.next != null) { //重点就是这个地方, 因为是完全二叉树, 然后马上就把刚连接完的next指针用上了, 值得学习
                root.right.next = root.next.left;
            }
        }
        
        connectHelper(root.left);
        connectHelper(root.right);
        return root;
    }
}
