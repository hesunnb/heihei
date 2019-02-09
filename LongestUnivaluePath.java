/*Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not 
pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output:

2
Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
             / \   
            4   4   
               /    
              4   
Output:

4
Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.*/

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
    int len = 0; // global variable
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getLen(root, root.val); //最后一次, val == root.val就是根与根比较并且+1, 但是最后返回的是len, 所以说最后这个关于根的+1的返回值
        //并没有加入到len, 就是没用到, 所以没事
        return len;
    }

    private int getLen(TreeNode node, int val) {
        if (node == null) {
            return 0;
        }
        int left = getLen(node.left, node.val);
        int right = getLen(node.right, node.val);
        len = Math.max(len, left + right); //只有父子节点相等的时候才会返回非0值, 每个子树的根节点都是和底下的全部连通的, 所以要左右子树
        //加和
        if (val == node.val)  {
            return Math.max(left, right) + 1; //左右选一个的原因是只能是单路径, 不能分叉, 见上图
        }
        return 0; //一旦父子节点不相等连不上, 就返回0
    }
}

