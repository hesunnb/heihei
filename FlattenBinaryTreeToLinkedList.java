/* Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6

The flattened tree should look like:

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
    public void flatten(TreeNode root) {
        
        if(root == null) {
            return;
        }
        
        TreeNode left = root.left; //拆分做节点
        TreeNode right = root.right; //拆分右节点
        
        root.left = null; //让root左节点为null, 左右节点前面已经保留下来了
        
        flatten(left); //flatten左子树
        flatten(right); //flatten右子树
        
        root.right = left; //交换左右两边已经flatten好的子树
        TreeNode cur = root;
        while(cur.right != null) { //让cur走到最有边下面
            cur = cur.right;
        }
        cur.right = right; //做连接
    }
    
    
    //tricky solution, 用了reverse preorder
    TreeNode pre = null;
    public void flatten(TreeNode root) {
        
        if(root == null) {
            return;
        }
        
        flatten(root.right); //这个方法是reverse preorder, 就是根左右的反向遍历右左根, 先是不断向右, 然后在回来向左, 最后在根处做处理
        flatten(root.left);
        root.right = pre; //根处做处理, right是之前的pre
        root.left = null; //左边按题意是null
        pre = root; //更新pre为自己
    }
}
