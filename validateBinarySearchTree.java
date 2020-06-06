/*Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
A single node tree is a BST

Example
An example:

  2
 / \
1   4
   / \
  3   5
The above binary tree is serialized as {2,1,4,#,#,3,5} (in level order).*/

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
     * @return: True if the binary tree is BST, or false
     */
     
    //version 1: inordertraversal, if ascending, is bst
    //思路:中序遍历一遍二叉树，如果是升序那就是二叉搜索树(Traverse)
    long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        
        return isValidBSTHelper(root);
    }
    
    public boolean isValidBSTHelper(TreeNode root) {
        if(root == null) {
            return true;
        }
        boolean left = isValidBSTHelper(root.left);
        
        if(prev >= root.val) {
            return false;
        }
        prev = root.val;
        
        boolean right = isValidBSTHelper(root.right);
        return left && right;
    }
  
    //version2
    int lastvalue = Integer.MIN_VALUE;
    boolean firstNode = true;
    public boolean isValidBST(TreeNode root) {
        // write your code here
        if(root == null) {
            return true;
        }
        //分为三步: 左是不是bst, 根的比较, 右是不是bst(左根右)
        if(!isValidBST(root.left)) {
            return false;    
        }
        if(!firstNode && lastvalue >= root.val) { //比较就是在这里完成的, 如果每次lastvalue都没有root.val大, 那就是升序
        //firstNode的作用在于第一次根节点的比较, 如果这棵树只是一个根节点, 且值为Integer
        //.MIN_VALUE, 那么这时候lastvalue和Integer.MIN_VALUE就是相等的, 然后就要返回false
        //, 但是只有一个根节点也是二叉搜索树, 所以加一个firstNode, 让第一次lastvalue和root.value不比较
        //二叉搜索树当中不能有重复节点, 比如已经有一个1, 这个树中就不能再有1啦, 1在左边或右边都是不对哒
            return false;
        }
        firstNode = false; //在这里第一次过后把firstNode改为假，这样上面就可以开始比较了
        lastvalue = root.val; //替换值，进行比较，看看是不是升序
        if(!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    //按照Crack书的方法, 用int的包装类Integer就能完成第一个节点Integer.MIN_VALUE的判断功能
    Integer lastvalue = null;
    public boolean isValidBST(TreeNode root) {
        // write your code here
        if(root == null) {
            return true;
        }
        //分为三步: 左是不是bst, 根的比较, 右是不是bst(左根右)
        if(!isValidBST(root.left)) {
            return false;    
        }
        if(lastvalue != null && lastvalue >= root.val) { //lastvalue至少要有值才能判断, 如果根是Integer.MIN_VALUE而lastvalue是
            //null的时候, 是不会判断大小的
            return false;
        }
        
        lastvalue = root.val; //替换值，进行比较，看看是不是升序
        if(!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
  
    /*Crack书的另一个方法, left.data < current.data < right.data for each node, 书上的例子:
    We start with a range of (min = NULL, max = NULL), which the root obviously meets. (NULL indicates
    that there is no min or max.) We then branch left, checking that these nodes are within the range ( min =
    NULL, max = 20). Then, we branch right, checking that the nodes are within the range ( min = 20,
    max = NULL).*/
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        
        return isValidBSTHelper(root, null, null);
    }
    
    public boolean isValidBSTHelper(TreeNode root, Integer min, Integer max) {
        if(root == null) {
            return true;
        }
        
        if(min != null && root.val <= min || max != null && root.val >= max) {
            return false;
        }
        
        if(!isValidBSTHelper(root.left, min, root.val) || !isValidBSTHelper(root.right, root.val, max)) {
            return false;
        }
        return true;
    }
  
    //version1的小改版不用firstNode, 直接把lastvalue改为long就可以了
    long lastvalue = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        if(!isValidBST(root.left)) {
            return false;    
        }
        if(lastvalue >= root.val) { 
            return false;
        }
        lastvalue = root.val;
        if(!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
  
    //Iterator, stack traversal(non-recursion)
    //和inordertravelsal的stack的方法一样, 就是中间弹出值后要判断
    public boolean isValidBST(TreeNode root) 
    {
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode pre = null;
         
        while(!st.isEmpty() || cur != null) {
            if(cur != null) {
                st.push(cur);
                cur = cur.left; //处理左, 左边有值, 不断装
            }
            else {
                TreeNode node = st.pop(); //处理根, 弹出来进行大小判断
                if(pre != null && node.val <= pre.val) {
                    return false;
                }
                pre = node;
                cur = node.right; //处理右, 右不为空就放进stack, 右为空就接着弹
            }
        }
        return true;
    }


    //囊括了Integer.MIN_VALUE和Integer.MAX_VALUE
    public boolean isValidBST(TreeNode root) {
        //用double的就是最大和最小的, 比Integer要大多啦, 所以就没有firstnode的问题
        //double必须用Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 和int与long不一样, 不能用MIN_VALUE, MAX_VALUE
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean helper(TreeNode p, long min, long max) {
        if(p == null) {
            return true;
        }
        
        //左边的值比传进来的值大就是false, 右边比传进来的值小就是false
        if(p.val <= min || p.val >= max) {
            return false;
        }
        
        //左边的min会一直保留, 右边的max会一直保留, helper就是判断BST的, 如果左边与右边同时都是BST, return真
        return helper(p.left, min, p.val) && helper(p.right, p.val, max);
    }
}
