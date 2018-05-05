/*Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key 
plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13*/
          
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    /*Since this is a BST, we can do a reverse inorder traversal to traverse the nodes of the tree in descending order. In the
    process, we keep track of the running sum of all nodes which we have traversed thus far.*/
    //solution1: 树的遍历除了典型的前中后还有一些变种
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        convertBSTHelper(root, 0);
        return root;
    }
    
    private int convertBSTHelper(TreeNode root, int sum) {
        if(root == null) {
            return sum;
        }
        sum = convertBSTHelper(root.right, sum);
        root.val += sum;
        sum = root.val;
        sum = convertBSTHelper(root.left, sum);
        return sum;
    }
  
  
    //solution2: iterative method
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int sum = 0;
        TreeNode cur = root; //因为最后还要返回这棵树, 所以root保留, 用个cur遍历树
        
        if(root == null) {
            return null;
        }
        
        while(cur != null || !stack.isEmpty()) { //最开始stack没有加进root, 所以要root!=null才能进入循环, 这里和前序后序不一样
            while(cur != null) { //右
                stack.push(cur);
                cur = cur.right;
            }
            
            cur = stack.pop(); //注意这里使用root完成的, 接下来还要加root的左节点
            cur.val += sum; //根
            sum = cur.val;
            cur = cur.left; //左
        }
        return root;
    }
  
  
    //solution3: morris中序遍历解法, 因为这道题是反向中序遍历以得到降序序列, 所以这个morris的遍历过程也要反过来
    public TreeNode convertBST(TreeNode root) {
        TreeNode cur = root, prev = null;
        int sum = 0;
        while(cur != null) {
            if (cur.right == null) {
                cur.val += sum;
                sum = cur.val;
                cur = cur.left;
            }
            else {
                // find predecessor
                prev = cur.right;
                while(prev.left != null && prev.left != cur) {
                    prev = prev.left;
                }
                    
                if (prev.left == null) {
                    prev.left = cur;
                    cur = cur.right;
                }
                else {
                    prev.left = null;
                    cur.val += sum;
                    sum = cur.val;
                    cur = cur.left;
                }
            }
        }
        return root;
    }
}
