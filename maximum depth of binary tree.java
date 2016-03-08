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
     
    //travsel的特点是把结果作为参数再传，给分治是先得到结果再选择
        
    //Divide & Conquer O(n) 都是把左右节点都遍历一遍(首选)
    public int maxDepth(TreeNode root) {
        // write your code here
        
       
        if(root == null)
        {
            return 0;
        }
        
        int left = maxDepth(root.left); //先求左子树最大
        int right =  maxDepth(root.right); //再求右子树最大
        return Math.max(left, right) + 1; //把最大值拿回来再加上自己的节点
 
    }
    
    //BFS就是层次遍历
    public int maxDepth(TreeNode root) {
        if(root == null)
        {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        
        q.offer(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            depth++;
            for(int i = 0; i < size; i++)
            {
                TreeNode temp = q.poll();
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
    
    //traversal O(n)
    int depth = 0;
    public int maxDepth(TreeNode root)
    {
        maxDepthhelper(root, 1);
        return depth;
    }
    
    public void maxDepthhelper(TreeNode node, int curdepth)
    {
        if(node == null)
        {
            return;
        }
        
        if(curdepth > depth)
        {
            depth = curdepth;
        }
        
        maxDepthhelper(node.left, curdepth + 1);
        maxDepthhelper(node.right, curdepth + 1);
    }
}
