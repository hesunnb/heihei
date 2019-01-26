/*Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as 
possible. It can have between 1 and 2h nodes inclusive at the last level h.

 

Example 1:

Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) 
are as far left as possible.

Example 2:

Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

 

Note:

    The tree will have between 1 and 100 nodes.*/
    
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
    
    /*思路:Use BFS to do a level order traversal,
    add childrens to the bfs queue,
    until we met the first empty node.

    For a complete binary tree,
    there should not be any node after we met an empty one.*/
    public boolean isCompleteTree(TreeNode root) {
        
        if(root == null) {
            return false;
        }

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);

        while(!Q.isEmpty()) { 
            int size = Q.size(); 
            for(int i = 0; i < size; i++) {
                if(Q.peek() != null) {
                    TreeNode tn = Q.poll();
                    Q.offer(tn.left); //把null也加入到队列
                    Q.offer(tn.right);   
                } else {
                    break; //直到遇到队列中第一个null就break
                }
            }
            if(Q.peek() == null) { //队列顶是null就break
                break;
            }
        }
        
        while(!Q.isEmpty()) { //如果是complete tree, 当队列顶有一个null之后, 后面的应该就都是null
            if(Q.peek() == null) {
                Q.poll();
            } else {
                break; //遇到了非null元素就break
            }
        }
        
        return Q.isEmpty(); //队列里有非null元素的话就会留在队列里, 那就不是complete tree了
    }
}
