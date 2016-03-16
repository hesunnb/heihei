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
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        
        //思路: 用分治来解决
        // 在root为根的二叉树中找A,B的LCA:
        // 如果找到了就返回这个LCA
        // 如果只碰到A，就返回A
        // 如果只碰到B，就返回B
        // 如果都没有，就返回null
        if(root == null || root == A || root == B)
        {
            return root; //只要找完一边或者找到一个，就马上返回，然后接着找另一边
        }
        
        //分别找左右子树的A和B
        TreeNode left = lowestCommonAncestor(root.left, A, B); 
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        
        //对于一个节点来说，这两个点要么在同一棵子树上，要么分别在两棵子树上
        if(left != null && right != null) //A,B分别在两棵子树上，返回自己
        {
            return root;
        }
        else if(left != null) //A,B都在左子树上，先遇到了谁，谁就是最近公共祖先
        {
            return left;
        }
        else if(right != null)//A,B都在右子树上，先遇到了谁，谁就是最近公共祖先
        {
            return right;
        }
        else
        {
            return null;
        }
    }
    
    
    
    
    
    //传统方法：就是有parent指针的方法
    public TreeNode lowestCommonAncestor(TreeNode node1, TreeNode node2) {
        //用两个Arraylist分别装给的两个节点的本身和他们的父节点
        ArrayList<TreeNode> list1 = getPath2Root(node1);
        ArrayList<TreeNode> list2 = getPath2Root(node2);
        
        int i, j;
        for (i = list1.size() - 1, j = list2.size() - 1; i >= 0 && j >= 0; i--, j--) {
            if (list1.get(i) != list2.get(j)) {
                return list1.get(i).parent; //两个Arraylist不同的地方就是岔开的地方, 岔开的任一元素的父节点就是公共祖先
            }
        }
        
        //(感觉list1与list2的判断还有退出循环之后的判断有问题)
        return list1.get(i+1);
    }
    
    //装本身和父节点的函数
    private ArrayList<TreeNode> getPath2Root(TreeNode node) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        while (node != null) {
            list.add(node);
            node = node.parent; //需要提供父节点
        }
        return list;
    }

}
