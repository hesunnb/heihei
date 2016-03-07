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
     * @return: Postorder in ArrayList which contains node values.
     */
     
    //version 1: traverse
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
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
        traversal(root.right, result);
        result.add(root.val);
    }
    
    
    
    //version 2: divide & conquer
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null)
        {
            return result;
        }
        
        ArrayList<Integer> left = postorderTraversal(root.left);
        ArrayList<Integer> right = postorderTraversal(root.right);
        
        result.addAll(left);
        result.addAll(right);
        result.add(root.val);
        
        return result;
    }
    
    
    
    //version 3: non-recursion
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> sk = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(root == null)
        {
            return result;
        }
        
        sk.push(root);
        while(!sk.isEmpty())
        {
            root = sk.pop();
            result.add(0, root.val); //每次在头加入值, 后序是左右根, 倒着就是根右左, 和前序非常向, 所以用根右左遍历, 然后倒着插入到result当中就可以啦！
            if(root.left != null)
            {
                sk.push(root.left);
            }
            if(root.right != null)
            {
                sk.push(root.right);   
            }
        }
        return result;
    }
    
    
    
    //version 4: iterative(用curr和prev)
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> sk = new Stack<TreeNode>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(root == null)
        {
            return result;
        }
        
        TreeNode curr = null;
        TreeNode prev = null;
        sk.push(root);
        while(!sk.isEmpty())
        {
            curr = sk.peek();
            if(prev == null || curr == prev.left || curr == prev.right) // traverse down the tree, 这三个就屎正序的条件, prev小于curr的时候, 正序向下找
            {
                if(curr.left != null) //左右根, 所以还是一顿向左找, 先加左
                {
                    sk.push(curr.left);
                }
                else if(curr.right != null) //左边没有啦, 再找右边, 再加右
                {
                    sk.push(curr.right);
                }
            }
            else if(curr.left == prev) // traverse up the tree from the left, prev大于curr的时候, 往回返(反序), 找完左边啦
            {
                if(curr.right != null) //找右边, 右边有值就加入
                {
                    sk.push(curr.right); //这时prev和curr又恢复啦正常得顺序继续找
                }
            }
            else // traverse up the tree from the right, 进入到这里就说明已经从右边回来啦
            {
                result.add(curr.val); //左右根, 每一次加值都是从右边回来之后把自己加进去
                sk.pop(); //prev和curr相等的时候弹值, 弹完之后就反序, 然后往回返
            }
            prev = curr;
        }
        return result;
    }
}
