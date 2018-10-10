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
    
    
    //非常棒的dfs解
    public List<Integer> rightSideView(TreeNode root) {
        /*The core idea of this algorithm:

        1.Each depth of the tree only select one node.
        2. View depth is current size of result list.*/
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        rightSideViewHelper(root, result, 0);
        return result;
    }
    
    public void rightSideViewHelper(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null) {
            return;
        }
        
        //每层只选一个, currDepth == result.size()相等的时候, 就是初到这层的时候, 又因为是跟右左的顺序, 所以加入的都是最右侧的值
        if(currDepth == result.size()) { //根右左的遍历顺序, 遍历优先向右走, 新到一层优先是右侧, 然后加值, 右侧没有才向左侧走
            result.add(curr.val);
        }
        
        rightSideViewHelper(curr.right, result, currDepth + 1);
        rightSideViewHelper(curr.left, result, currDepth + 1);
        
    }
}
