/*Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.*/

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
    
    //solution1: 递归
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int sum = 0;
        return sumOfLeftLeavesHelper(root.left, root.right, sum);
    }
    
    private int sumOfLeftLeavesHelper(TreeNode left, TreeNode right, int sum) {
        
        if(left != null) {
            if(left.left == null && left.right == null) { //确保是左叶子节点
                sum += left.val; //加入值
            }
            sum = sumOfLeftLeavesHelper(left.left, left.right, sum); //return回来的sum必须用sum接住, 因为sum在后面加完那些
            //叶子节点之后值会改变, 跟当前栈的sum值早已不同, 所以要回来接住改变当前栈中sum的值
        }
        if(right != null) {
            sum = sumOfLeftLeavesHelper(right.left, right.right, sum);
        }
        return sum;
    }
    
    
    //solution2: 栈, 遍历法
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.right);
        stack.push(root.left);
        
        while(!stack.isEmpty()) { //因为放每次就是放两个, 弹出也是每次弹出两个, 所以不会有left和right与弹出来的对不上的时候
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if(left != null) {
                if(left.left == null && left.right == null) { //确保是左叶子节点
                    sum += left.val; //加入值
                }
                stack.push(left.right);
                stack.push(left.left);
            }
            
            if(right != null) {
                stack.push(right.right);
                stack.push(right.left);
            }
        }
        return sum;
    }
}
