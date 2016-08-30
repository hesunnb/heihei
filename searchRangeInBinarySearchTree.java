/*Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. 
Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST. 
Return all the keys in ascending order.

Example
If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].

    20
   /  \
  8   22
 / \
4   12*/

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
     * @param root: The root of the binary search tree.
     * @param k1 and k2: range k1 to k2.
     * @return: Return all keys that k1<=key<=k2 in ascending order.
     */
    
    //思路就是中序遍历一遍
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        helper(result, root, k1, k2);
        return result;
    }
    
    private void helper(ArrayList<Integer> result, TreeNode root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.val > k1) {
            helper(result, root.left, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            result.add(root.val);
        }
        if (root.val < k2) {
            helper(result, root.right, k1, k2);
        }
    }
}
