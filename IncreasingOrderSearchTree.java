/*Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no 
left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9  
Note:

The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    //own method: 用prev保留之前的父节点, newRoot是一个dummyNode, newRoot.right是新根
    TreeNode newRoot;
    TreeNode prev;
    public TreeNode increasingBST(TreeNode root) {
        if(root == null) {
            return root;
        }
        newRoot = new TreeNode(0);
        prev = newRoot; //最开始让newRoot和prev在一个地方
        increasingBSTHelper(root);
        return newRoot.right;
    }
    
    private void increasingBSTHelper(TreeNode root) {
        if(root == null) {
            return;
        }
        increasingBSTHelper(root.left);
        if(newRoot == prev) { //当找到第一个节点的时候, 就是新根, 这时newRoot和prev还在同一位置, 那么就赋值新根, 接下来prev的位置就变了,
            //这里就不会再被执行, 新根也就得到了保留
            newRoot.right = root;
        }
        root.left = null; //注意这里一定要把root.left改为null, 否则test的时候重新遍历这棵数就错了, 因为父指向子, 子指向父了
        prev.right = root;
        prev = root;
        increasingBSTHelper(root.right); //root.right是不用改的
    }
       
       
    //solution2: 值得学习的方法, tail是last node
    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

    private TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) {
            return tail; //root是null的时候返回tail
        }
        TreeNode res = increasingBST(root.left, root); //对于左边是root的左边和root, 左边一直保持着新的根往上传(新的根包括每一个右子树的根)
        root.left = null;
        root.right = increasingBST(root.right, tail); //对于右边是root的右边和tail
        return res;
    }
}
