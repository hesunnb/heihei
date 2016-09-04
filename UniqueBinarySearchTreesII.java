/*Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

Example
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3*/
   
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
     * @paramn n: An integer
     * @return: A list of root
     */
     
    //时间的话就是(O(二叉搜索树的个数 * n))
    public List<TreeNode> generateTrees(int n) {
        // write your code here

        return helper(1, n);
    }
    
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        
        if(start > end) { //start与end相等的时候是叶子节点, start > end的时候是过了叶子节点, 就是下面的null
            result.add(null);
            return result;
        }
        
        for(int i = start; i <= end; i++) { //当start和end相等的时候就是到了叶子节点, 可以加节点的时候
            List<TreeNode> left = helper(start, i - 1); //左子树都是二叉搜索树
            List<TreeNode> right = helper(i + 1, end); //右子树都是二叉搜索树
            for(TreeNode l : left) { //组合左右的二叉搜索树
                for(TreeNode r : right) {
                    // should new a root here because it need to 
                    // be different for each tree
                    TreeNode root = new TreeNode(i); //连接到根的两侧
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
