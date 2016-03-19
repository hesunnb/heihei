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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
public class BSTIterator {
    
    //等价于写非递归的中序遍历
    
    //Extra memory usage O(h), h is the height of the tree: 比如这棵树全是左子树, 那么一次向stack中加入的就是这个树的高
    //度, 其余的时候stack都会有弹出, 用的都比O(h)要小
    
    //@param root: The root of binary tree.
    private TreeNode curt = null;
    private Stack<TreeNode> st = new Stack<TreeNode>();
    public BSTIterator(TreeNode root) { //这是构造函数
        // write your code here
        curt = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        // write your code here
        return(curt != null || !st.isEmpty());
    }
    
    //@return: return next node
    public TreeNode next() {
        // write your code here
        while(curt != null) //这个地方不能加!st.isEmpty(), 不能完全复制上面的条件, 因为有cur为空而stack不为空的情况, 
        //要是用上面的条件, 就会null = null.left啦
        {
            st.push(curt); //从根开始一连串把根左都加入栈
            curt = curt.left;
        }
        
        curt = st.pop(); //一个一个取出来，取出一个同时看这个点的右侧节点，返回上面接着取
        TreeNode node = curt;
        curt = curt.right;
        return node;
        //这个地方可以再简练一点: (4句变3句)
        TreeNode node = st.pop();
        curt = node.right;
        return node;
    }
}
