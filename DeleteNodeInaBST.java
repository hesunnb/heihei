/*Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly 
updated) of the BST.

Basically, the deletion can be divided into two stages:

    Search for a node to remove.
    If the node is found, delete the node.

Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        if(key < root.val) {
            root.left = deleteNode(root.left, key); //用左节点, 右节点这种接的方法非常好, 分治带回子树
        } else if(key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if(root.left == null) {
                return root.right; //左边为null, 直接返回右子树, 连接到上级的父节点(分治)
            } else if(root.right == null) {
                return root.left; //同理
            }

            //两边都不为null
            TreeNode minNode = findMin(root.right); //找右子树的最小值
            root.val = minNode.val; //与要删的节点换值
            root.right = deleteNode(root.right, root.val); //再删除最小值(最后删除的一定是叶子节点, 所以就在上面左右为null的判断那里就返回了)
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}
