/*Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, 
or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

 

Example 1:



Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
Example 2:



Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the 
root of the tree to the right child.
Example 3:



Input: [1,0,2]
Output: 2
Example 4:



Input: [1,0,0,null,3]
Output: 4
 

Note:

1<= N <= 100
0 <= node.val <= N*/

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
    
    /*Helper function returns the amount of coins each node need or have excessively(多余的). For each node, it will try to balance 
    the amount of the coins used by its left child and right child.

    And it will return a positive number if there is excessive coins which could be used by its parent node, or a negative number 
    if current node or its children need coins.

    Each coin (excessive or needed) need one step to be sent to the parent node.
    思路就是子节点要返回自己的coin, 多余的返回多余的coin, 不足的需要硬币的返回-1(向上传-1就等于向下传硬币), 当返回到某个父节点的时候, 计算之后如果
    父节点要向上返回的coin为0, 说明这课子树自己就可以完成分配所有的coin了*/
    private int result = 0; 

    public int distributeCoins(TreeNode root) {
        postorder(root);
        return result;
    }

    // return coins of this level
    private int postorder(TreeNode node) {
        if(node == null) {
            return 0;
        }

        // coin from children -- post-order traversal
        int coin = postorder(node.left) + postorder(node.right);

        // current node coin
        if(node.val == 0) {
            coin += -1; // current node need one coin
        }
        else {
            coin += node.val - 1; // keep one coin for current node
        }

        result += Math.abs(coin); // each coin move up to parent node need 1 step
        return coin; 
    }
}
