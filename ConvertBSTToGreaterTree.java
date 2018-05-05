/*Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key 
plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13*/
          
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
    
    /*Since this is a BST, we can do a reverse inorder traversal to traverse the nodes of the tree in descending order. In the
    process, we keep track of the running sum of all nodes which we have traversed thus far.*/
    //树的遍历除了典型的前中后还有一些变种
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        convertBSTHelper(root, 0);
        return root;
    }
    
    private int convertBSTHelper(TreeNode root, int sum) {
        if(root == null) {
            return sum;
        }
        sum = convertBSTHelper(root.right, sum);
        root.val += sum;
        sum = root.val;
        sum = convertBSTHelper(root.left, sum);
        return sum;
    }
}
