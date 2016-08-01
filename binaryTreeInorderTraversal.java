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
     * @return: Inorder in ArrayList which contains node values.
     */
     
    //这种遍历和下面的分治法都是递归, 区别是遍历把result作为参数传来传去, 并在递归的过程当中进行修改; 
    //分治是把result作为return值返回, 不作为参数, 分治方法对于树更通用
    //version 1: divide & conquer
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
        {
            return result;
        }
        
        ArrayList<Integer> left = inorderTraversal(root.left); //得到root.left的result
        ArrayList<Integer> right = inorderTraversal(root.right); //得到root.right的result
        
        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        
        return result;
    }
    
    
    
    //version 2: non_recursion(2个while)
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> sk = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(root == null)
        {
            return result;
        }
        
        while(root != null || !sk.isEmpty()) //最开始stack没有加进root, 所以要root!=null才能进入循环, 这里和前序后序不一样
        {
            while(root != null) //左
            {
                sk.push(root);
                root = root.left;
            }
            
            root = sk.pop(); //注意这里使用root完成的, 接下来还要加root的右节点
            result.add(root.val); //根
            root = root.right; //右
        }
        return result;
    }
    
    
    //version 3: traverse遍历
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
        {
            return result;
        }
        traversal(root, result);
        return result;
    }
    
    private void traversal(TreeNode root, ArrayList<Integer> result)
    {
        if(root == null)
        {
            return;
        }
        
        traversal(root.left, result);
        result.add(root.val);
        traversal(root.right, result);
    }
    
    
    //version 3: non-recursion(自己代码, 3个while)
    /**public ArrayList<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> sk = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(root == null)
        {
            return result;
        }
        
        while(root != null)
        {
            sk.push(root);
            root = root.left;
        }
            
        while(!sk.isEmpty())
        {
            TreeNode node = sk.pop();
            result.add(node.val);
            if(node.right != null)
            {
                node = node.right;
                sk.push(node);
                while(node.left != null)
                {
                    node = node.left;
                    sk.push(node);
                }
            }
        }
        return result;
    }*/
}
