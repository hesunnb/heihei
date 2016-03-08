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
     * @param root: The root of binary tree.
     * @return: An integer.
     */
     
    
    //version 1: DFS
    public int minDepth(TreeNode root) {
        // write your code here
        if(root==null)
        {
            return 0;
        }
        if(root.left==null) //因为最短距离是到叶子节点, 所以要把左子树, 右子树空的地方给躲过去, 所以左子树为空, 不找左子树, 找右子树最小
        {
            return minDepth(root.right)+1;
        }
        if(root.right==null) //右子树为空, 不找右子树, 找左子树最小
        {
            return minDepth(root.left)+1;
        }
        return Math.min(minDepth(root.left),minDepth(root.right))+1; //左右都不为空, 左右子树最小都要找, 然后挑出最小
    }
    
    
    //version 2: BFS
    public int minDepth(TreeNode root) 
    {
        if(root==null)
        {
            return 0;
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int depth = 0;
        
        q.offer(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            depth++;
            for(int i = 0; i < size; i++)
            {
                TreeNode temp = q.poll();
                if(temp.left == null && temp.right == null)
                {
                    return depth;
                }
                if(temp.left != null)
                {
                    q.offer(temp.left);
                }
                if(temp.right != null)
                {
                    q.offer(temp.right);
                }
            }
        }
        return depth;
    }
}
