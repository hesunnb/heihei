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

    //序列化方法: 把树序列化成字符串, 用map来判断重复, 非常好
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        serialize(root, new HashMap<>(), res);
        return res;
    }

    private String serialize(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) {
            return "#";
        }  
        String serial = cur.val + "," + serialize(cur.left, map, res) + "," + serialize(cur.right, map, res);
        if (map.getOrDefault(serial, 0) == 1) { //如果要是重复了, ==1是重复, 再重复就是2,3...但是与1不相等, 所以即使重复也不加入到res当中
            res.add(cur); //加入这个重复子树的根节点
        }
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
        
        
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
            set.add(T1); //因为duplicate的树加入到result中只需要一个, 所以要用set去重
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
