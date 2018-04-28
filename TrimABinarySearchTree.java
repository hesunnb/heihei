/*Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] 
(R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2
Example 2:
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1*/
 
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
    
    //solution1: 递归
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null || L > R) {
            return root;
        }
        
        return trimBSTHelper(root, L, R);
    }
    
    private TreeNode trimBSTHelper(TreeNode root, int L, int R) {
        if(root == null) {
            return root;
        }
        if(root.val > R) { //利用BST的性质, 如果root.val不在L与R之间, 那么就往下找, 如果找到了就从找到的节点开始, 并返回找到的节点
            //如果一直找也找不到, 那么就会一直到root==null, 直接返回null了
            return trimBSTHelper(root.left, L, R);
        }
        if(root.val < L) {
            return trimBSTHelper(root.right, L, R);
        }
        //如果root.val既不大于R, 又不小于L, 那么root.val一定>=L && <=R, 所以操作这个节点就可以了
        root.left = trimBSTHelper(root.left, L, R);
        root.right = trimBSTHelper(root.right, L, R);
        return root;
    }
    
    
    //solution2: 迭代法, 也是通过移除左右子树的方式进行循环
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null || L > R) {
            return null;
        }
        //Find a valid root which is used to return.
        while (root.val < L || root.val > R) {
            if (root.val < L) {
                root = root.right;
                if(root == null) break; //比如树是[1,0,2], 边界是5,6这种情况就break, 防止访问null的val
            }
            if (root.val > R) {
                root = root.left;
                if(root == null) break;
            }
        }
        
        TreeNode dummy = root;
        // Remove the invalid nodes from left subtree.
        while (dummy != null) {
            while (dummy.left != null && dummy.left.val < L) {
                dummy.left = dummy.left.right; 
                // If the left child is smaller than L, then we just keep the right subtree of it. 
            }
            dummy = dummy.left;
        }
        dummy = root;
        // Remove the invalid nodes from right subtree
        while (dummy != null) {
            while (dummy.right != null && dummy.right.val > R) {
                dummy.right = dummy.right.left;
                // If the right child is biggrt than R, then we just keep the left subtree of it. 
            }
            dummy = dummy.right;
        }
        return root;
    }
}
