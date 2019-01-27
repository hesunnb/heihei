/*Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as 
possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6*/

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
    
    //因为要统计完全二叉树节点的个数, 所以一定与公式有关, bfs显然不是想要的
    /*Since I halve(对分) the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). 
    So overall O(log(n)^2).
    对分指的是每次找到一个"子"完全二叉树, 就相当于截掉了这个树, 递归总会找到新的子完全二叉树, 然后就一半一半的截掉了*/
    public int countNodes(TreeNode root) {
        
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);

        if (leftDepth == rightDepth) { //因为是完全二叉树, 所以可以通过看树最左边的深度和树最右边的深度是不是相等来截取出部分完全二叉树, 
            //然后用数学公式简化运算, 不用遍历整个树
            return (1 << leftDepth) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right); //用递归来分解整个树, 这样就能找到"子"完全二叉树了
        }  
    }
    
    private int leftDepth(TreeNode root) { //一直向左走的深度
        int dep = 0;
        while (root != null) {
            dep++;
            root = root.left;
        }
        return dep;
    }
    
    private int rightDepth(TreeNode root) { //一直向右走的深度
        int dep = 0;
        while (root != null) {
            dep++;
            root = root.right;
        }
        return dep;
    }    
}
