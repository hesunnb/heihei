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
     * @return: Preorder in ArrayList which contains node values.
     */
     
//方法一: 递归方法:(recursion)traverse遍历, 这种遍历和下面的分治法都是递归, 区别是遍历把result作为参数传来传去, 
    //并在递归的过程当中进行修改; 分治是把result作为return值返回, 不作为参数, 分治方法对于树更通用
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        travelsal(root, result);
        return result;
    }
    
    //把root为根的preorder加入result里面
    public void travelsal(TreeNode root, ArrayList<Integer> result)
    {
        if(root == null)
        {
            return;
        }
        
        result.add(root.val);
        travelsal(root.left, result);
        travelsal(root.right, result);
    }
    
    
    
//方法二: 分治法
    public ArrayList<Integer> preorderTraversal(TreeNode root) 
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
        {
            return result;
        }
        
        //Divide:这个就是分治的策略，包括每个节点的分治；为了得到根的前序，先得到根的左子树的前序，然后得到根的右子树的前序，以此类推
        ArrayList<Integer> left = preorderTraversal(root.left); 
        ArrayList<Integer> right = preorderTraversal(root.right);
        
        //Conquer:整合的过程，包括每一个节点自己的整合；在得到值之后，先加入自己的值，然后加入左子树的值，再加入右子树的值
        result.add(root.val); //加入自己的值
        result.addAll(left); //加入所有左子树的值,addAll括号里面是的left没有元素的话，是可以的，那就不向result中加任何东西, 
        //addAll就是向result当中加入所有left中的元素
        result.addAll(right); //加入所有右子树的值
        return result;
    }
    
    
    
    //version3: 非递归
    public ArrayList<Integer> preorderTraversal(TreeNode root)
    {
        Stack<TreeNode> sk = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
        {
            return result;
        }
        
        sk.push(root); //先放根
        while(!sk.isEmpty())
        {
            TreeNode node = sk.pop();
            result.add(node.val); 
            if(node.right != null) //根左右, 所以栈的话先放右, 再放左, 弹出来的时候就是先左后右; 连续操作, 先放右边
            {
                sk.push(node.right);
            }
            if(node.left != null) //接着放左边
            {
                sk.push(node.left);
            }
        }
        return result;
    }
}
