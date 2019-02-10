/*Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while 
the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of 
the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
Note: The merging process must start from the root nodes of both trees.*/

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

    //solution1: discuss管这个方法叫shared node, 就是谁在那个节点处有值就取谁, 其实就是从头到尾自己创建了一棵树出来
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
	

    //solution2: (own), 用construct tree的原因是自己在mergeTrees里面递归的时候, 新建完的节点还要值加和覆盖掉t1新建节点的值, 所以为了
    //避免覆盖, 单独弄了一个construct tree的函数
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) { //因为传入的时候t1不可能为空, 只有一开始的根节点有可能
            return t2; //所以直接返回t2就行
        }
        if(t2 == null) { //t2为空是有可能的
            return t1; //返回t1
        }
        if(t1.left != null) { //左不为空往左
            mergeTrees(t1.left, t2.left);
        }
        if(t1.right != null) { //右不为空往右
            mergeTrees(t1.right, t2.right);
        }
        if(t1 != null && t2 != null) { //加和, 整棵树是t2merge到t1
            t1.val += t2.val;
        }
        if(t1.left == null && t2.left != null) { //如果需要建节点
            t1.left = new TreeNode(t2.left.val); //建节点
            constructTree(t1.left, t2.left); //继续构造后面的
        }
        if(t1.right == null && t2.right != null) {
            t1.right = new TreeNode(t2.right.val);
            constructTree(t1.right, t2.right);
        }
        return t1; //最终返回的也是t1
    }
    
    private void constructTree(TreeNode t1, TreeNode t2) {
        if(t1.left == null && t2.left != null) {
            t1.left = new TreeNode(t2.left.val);
            constructTree(t1.left, t2.left);
        }
        if(t1.right == null && t2.right != null) {
            t1.right = new TreeNode(t2.right.val);
            constructTree(t1.right, t2.right);
        }
    }
}
