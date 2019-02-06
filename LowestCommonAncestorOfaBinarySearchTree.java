/*Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node 
in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]

 

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.

Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

 

Note:

    All of the nodes' values will be unique.
    p and q are different and both values will exist in the BST.

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
class Solution {
    
    //自己思考的更全面: p与q的比较和null的返回都有
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        
        if(p.val > q.val) { //题目中并没有说p和q谁大, 所以要指定一下, 让p总是小的, q是大的
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        return lowestCommonAncestorHelper(root, p, q);
    }
    
    private TreeNode lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return root;
        }
        
        if(root.val >= p.val && root.val <= q.val) {
            return root;
        } else if(root.val < p.val){
            TreeNode node = lowestCommonAncestorHelper(root.right, p, q);
            if(node != null) {
                return node;
            }
        } else if(root.val > q.val) {
            TreeNode node = lowestCommonAncestorHelper(root.left, p, q);
            if(node != null) {
                return node;
            }
        }
        return null;
    }
 
    //都说是二叉搜索树了, 所以一定和树的性质有关, 一般的二叉树就只能遇到pq然后比值相等了
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val) { //根的值比左右pq都大, 往左走
            return lowestCommonAncestor(root.left, p, q);
        } else if(root.val < p.val && root.val < q.val) { //根的值比左右pq都小, 往右走
            return lowestCommonAncestor(root.right, p, q);
        } else{
            return root; //root的值在pq之间的时候, 那就是root, 因为无论pq在root左右什么地方, 最后都是root
        }
    }
}
