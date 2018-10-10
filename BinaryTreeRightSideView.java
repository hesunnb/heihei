/*Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to 
bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/

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

    //bfs解, 就看每一层最右边的就行了
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);

        while(!Q.isEmpty()) { //有点就继续，没有点就退出了，说明刚刚加进来的点没有子节点了
            int size = Q.size(); //为的就是保留上次size的值，不能让size随着节点数的增加而实时变化
            for(int i = 0; i < size; i++) {
                TreeNode tn = Q.poll();
                if(i == size-1) {
                    result.add(tn.val);
                }

                if(tn.left != null) {
                    Q.offer(tn.left);
                }
                if(tn.right != null) {
                    Q.offer(tn.right);
                }
            }
        }
        return result;
    }
}
