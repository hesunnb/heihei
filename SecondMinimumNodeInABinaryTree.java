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
    
    //solution1: dfs
    public int findSecondMinimumValue(TreeNode root) {
        
        if(root == null) { //因为这道题限制了每个节点会有要么2个子节点, 要么0个子节点, 所以这个root为空的情况就只针对于最开始的根节点, 
            //其余的时候用不到
            return -1;
        }
        if(root.left == null && root.right == null) {
            return -1; //返回-1的原因是所有节点都相等的时候直接返回-1方便
        }

        int left = root.left.val; //获取节点的左值
        int right = root.right.val; //获取节点的右值

        // if value same as root value, need to find the next candidate
        if(root.left.val == root.val) { //相等的话就继续向下找, 不等的话就保留当前节点左右子节点的值
            left = findSecondMinimumValue(root.left);
        }
        if(root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }

        if(left != -1 && right != -1) {
            return Math.min(left, right); //根节点左右子树都返回值的时候到这里决策
        } else if(left != -1) { //这里左右不分先后, 因为上一步是左右都不为-1, 到这步肯定左右有一个为-1, 谁不为-1返回谁
            return left;
        } else {
            return right;
        }
    }
    
    
    //solution2: bfs(own), 要用到一个result[]数组和一个队列
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
