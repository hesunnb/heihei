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
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
     
     
    //不带返回值: 先交换, 再往下去
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if(root == null)
        {
            return;
        }
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
    }
    
    
    
    
    
    
    
    //带返回值version 1: 先往下去, 再交换
    public TreeNode invertTree(TreeNode root) 
    {
        if(root == null)
        {
            return null;
        }
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
    }
    
    //带返回值version 2: 用stack实现
    public TreeNode invertTree(TreeNode root) 
    {
        if(root == null)
        {
            return null;
        }
        
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.add(root);
        while(!st.isEmpty())
        {
            TreeNode node = st.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left != null)
            {
                st.add(node.left);
            }
            if(node.right != null)
            {
                st.add(node.right);
            }
        }
        return root;
    }
}
