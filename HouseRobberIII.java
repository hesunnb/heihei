/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that 
"all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken
into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example

  3
 / \
2   3
 \   \ 
  3   1

Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

    3
   / \
  4   5
 / \   \ 
1   3   1

Maximum amount of money the thief can rob = 4 + 5 = 9.
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
     
    //dp[i][0]表示以i为根的子树不偷根节点能获得的最高价值，dp[i][1]表示以i为根的子树偷根节点能获得的最高价值
    public int houseRobber3(TreeNode root) {
        // write your code here
        int[] result = visit(root);
        return Math.max(result[0], result[1]);
    }
    
    private int[] visit(TreeNode root) {
        if(root == null) {
            int[] now = {0,0};
            return now;
        }
        
        int[] left = visit(root.left);
        int[] right = visit(root.right);
        int[] now = new int[2];
        now[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); //没有偷根节点的情况, 就选择左右子树中大的相加就行啦
        now[1] = left[0] + right[0] + root.val; //偷了根节点的话, 就只能用左右子树没有偷根节点的情况(防止相邻), 再加上自己的值
        return now;
    }
}
