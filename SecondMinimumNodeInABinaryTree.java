/*Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or 
zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.*/

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
    public int findSecondMinimumValue(TreeNode root) {
        
        int[] result = new int[2];
        if(root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        result[0] = root.val;
        result[1] = Integer.MAX_VALUE;

        while(!queue.isEmpty()) { //有点就继续，没有点就退出了，说明刚刚加进来的点没有子节点了
            int size = queue.size(); //为的就是保留上次size的值，不能让size随着节点数的增加而实时变化
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.val < result[1] && node.val != result[0]) { //result[0]装的是root的值, root的值一定是最小值, 如果和root值相等
                //的值就不放到result[1]中, 然后除了root之外的值不断找小的更新到result[1]中, 便是第二小的值
                    result[1] = node.val;
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return Integer.MAX_VALUE == result[1] ? -1 : result[1];
    }
}
