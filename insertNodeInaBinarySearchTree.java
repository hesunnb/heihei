/*Given a binary search tree and a new tree node, insert the node into the tree. 
You should keep the tree still be a valid binary search tree.
Notice

You can assume there is no duplicate values in this tree + node.

Example

Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6
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
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    
    //递归非递归都是O(logn)
    //每次都只会进入左子树或者右子树中的一个, 继续进行到其中一个子树, 还会接着选择左子树或者右子树中的一个,
    //每次都会砍掉一半的树(除以2), 所以O(logn)
    //递归:
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        
        if(root == null) {
            return node; //如果根为空，要插入的节点便直接成了根；其余情况node直接作为左右节点返回了,node本身不能是空
        }
        
        if(root.val < node.val) {
            root.right = insertNode(root.right, node); //把左边节点作为根传进去
        } else {
            root.left = insertNode(root.left, node); //右节点作为根传进去
        }
        return root; //返回root, 整体并不需要遍历整棵树
    }
    
    
    //非递归
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        
        if(root == null) {
            return node; //如果根为空，要插入的节点便直接成了根；其余情况node直接作为左右节点返回了,node本身不能是空
        }
        
        TreeNode temp = root;
        TreeNode last = temp;
        while(temp != null) {
            last = temp; //last保留上一个节点
            if(temp.val < node.val) { //temp负责向下查找，找到temp就为空啦，退出循环
                temp = temp.right;
            } else { //如果不加else这里就会连续判断了，temp的值刚改变就参与到下一个判断了
                temp = temp.left;
            }
        }
        if(last.val < node.val) {
            last.right = node;
        } else {
            last.left = node;
        }
        return root;
    }
}
