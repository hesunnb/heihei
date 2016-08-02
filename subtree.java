/*You have two every large binary trees: T1, with millions of nodes, and T2, with hundreds of nodes. 
Create an algorithm to decide if T2 is a subtree of T1. 
Example

T2 is a subtree of T1 in the following case:

       1                3
      / \              / 
T1 = 2   3      T2 =  4
        /
       4

T2 isn't a subtree of T1 in the following case:

       1               3
      / \               \
T1 = 2   3       T2 =    4
        /
       4
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
 
public class Solution {
    /**
     * @param T1, T2: The roots of binary tree.
     * @return: True if T2 is a subtree of T1, or false.
     */
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        
        if (T2 == null) { //null是任何树的子树
            return true;
        }
        if (T1 == null) {
            return false;
        }
        
        if (isEqual(T1, T2)) {
            return true;
        }
        if (isSubtree(T1.left, T2) || isSubtree(T1.right, T2)) { //左子树与右子树有一个和T2一样的, 就返回真; 短路或, 有一个为真递归就终止了
            return true;
        }
        return false;
    }
    
    
    private boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        
        if (a == null || b == null) {
            return a == b;
        }
        if (a.val != b.val) { //起到首节点判断的作用, 首节点不相等就不向下递归了
            return false;
        }
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
}
