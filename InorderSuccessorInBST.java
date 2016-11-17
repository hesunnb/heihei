/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
   38
  /   \
26     62
 \     / \
  35  50  94
  /    \
 28     55
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null && root.val != p.val) { //上面这个树满足各种情况
            if (root.val > p.val) { //root的值比所给节点值要大
                successor = root; //可能是它的后继, 先给上
                root = root.left;
            } else {
                root = root.right; //root的值没有所给节点值大, 不可能是后继, 向右窜
            }
        }
        
        if (root == null) { //所给的值不在树中, root就会为null, 直接返回null
            return null;
        }
        
        if (root.right == null) { //如果找到值它的右边没有比它大的了, 说明它的后继在上方, 就是之前的successor
            return successor;
        }
        
        root = root.right; //如果右边还有比它大的, 那就向右窜一位
        while (root.left != null) { //然后不断向左找, 找到头就是它的后继
            root = root.left;
        }
        
        return root;
    }
}
