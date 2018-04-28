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
        if(root.val > R) { //利用BST的性质, 如果root.val不在L与R之间, 那么就往下找, 如果找到了就new新的节点, 然后返回新的节点
            //如果一直找也找不到, 那么就会一直到root==null, 直接返回null了
            return trimBSTHelper(root.left, L, R);
        }
        if(root.val < L) {
            return trimBSTHelper(root.right, L, R);
        }
        //如果root.val既不大于R, 又不小于L, 那么root.val一定>=L && <=R, 所以直接new新的节点就可以了
        TreeNode node = new TreeNode(root.val);
        node.left = trimBSTHelper(root.left, L, R);
        node.right = trimBSTHelper(root.right, L, R);
        return node;
    }
}
