/*Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any 
one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.*/

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

    //一个疯狂的解法: 树套树套树, 套了3回
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        
        findDuplicateSubtreesHelper(root, root, result, new HashSet<TreeNode>());
        return result;
    }
    
    public void findDuplicateSubtreesHelper(TreeNode oriRoot, TreeNode root, List<TreeNode> result, Set<TreeNode> set) { //保证oriRoot
        //不变, 然后另一个root出发开始递归判断, 从每一个节点root确定一个子树, 然后让从oriRoot出发的原有的树与root的子树找subtree
        if(root == null) {
            return;
        }
        findDuplicateSubtreesHelper(oriRoot, root.left, result, set);
        findDuplicateSubtreesHelper(oriRoot, root.right, result, set);
        isSubtree(oriRoot, root, result, set);
    }
    
    public void isSubtree(TreeNode T1, TreeNode T2, List<TreeNode> result, Set<TreeNode> set) {
        if (T1 == null || T1 == T2) {
            return;
        }
        if (isIdentical(T1, T2)) {
            if(!set.contains(T1) && !set.contains(T2)) {
                result.add(T1);
            }
            set.add(T1);
            set.add(T2);
        }
        isSubtree(T1.left, T2, result, set);
        isSubtree(T1.right, T2, result, set);
    }
    
    private boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        
        if (a == null || b == null) {
            return a == b;
        }
        if (a.val != b.val) { //起到首节点判断的作用, 首节点不相等就不向下递归了
            return false;
        }
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
}
