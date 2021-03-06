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
     
     
    //version1 不带返回值: 从下到上进行翻转
    public void invertBinaryTree(TreeNode root) {
        // write your code here
        if(root == null)
        {
            return;
        }
        
        invertBinaryTree(root.left);
        invertBinaryTree(root.right);
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    
    
    //version2 带返回值: 从下到上翻转
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
    
    
    //version 3: 用stack实现
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
