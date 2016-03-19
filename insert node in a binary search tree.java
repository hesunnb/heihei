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
    //非递归
    public TreeNode insertNode(TreeNode root, TreeNode node)
    {
       if(root == null)
       {
           return node; //node本身应该不能是空
       }
       TreeNode temp = root;
       TreeNode last = null;
       
       while(temp != null)
       {
            last = temp; //last保留上一个节点
            if(temp.val > node.val) //temp负责向下查找，找到temp就为空啦，退出循环
            {
                temp = temp.left;
            }
            else if(temp.val < node.val) //如果不加else这里就会连续判断了，temp的值刚改变就参与到下一个判断了
            {
                temp = temp.right;
            }
       }
       if(last != null) //从last点开始把node加进去,last肯定不是空，所以这个判断可以没有
       {
           if(last.val > node.val)
           {
               last.left = node;
           }
           else if(last.val < node.val)
           {
               last.right = node;
           }
       }
       return root;
    }
     
     
    //递归:
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
   
        if(root == null)
        {
            return node; //如果根为空，要插入的节点便直接成了根；其余情况node直接作为左右节点返回了,node本身应该不能是空
        }
        
        if(root.val > node.val)
        {
            root.left = insertNode(root.left, node); //把左边节点作为根传进去
        }
        else
        {
            root.right = insertNode(root.right, node); //右节点作为根传进去
        }
        return root; //上面赋值除了node，其余情况要用root顶上
    }
}