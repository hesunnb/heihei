/*Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the 
given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False*/

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
    
    //solution1: 因为是二叉搜索树, 所以从根到尾找一遍用logn的时间, 总体时间nlogn; 空间O(h), h是树的高度, 这个空间是考虑递归所占用的空间
    //(就是开栈, 要往栈里面放树的节点, 访问过多少个节点就有多少节点入栈出栈),
    //h是树的高度, logn是最好的情况, n是最差的情况, 如果二叉搜索树从中间开始完美的向两边展开, 并且是完全二叉树, 那么搜索一次用logn, 如果树
    //是一条直线下来, 那么就是n了
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root,  k);
    }
    
    public boolean dfs(TreeNode root,  TreeNode cur, int k){
        if(cur == null) {
            return false; 
        }
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k); //根左右的递归顺序, cur
        //每次走一位, root走全树
    }
    
    public boolean search(TreeNode root, TreeNode cur, int value){
        if(root == null) {
            return false;
        }
        if(root.val == value && root != cur) { //比如说k=5, 那么根节点就是5, 题目要求两个节点和, 再有一个0可以, 但不能只是一个节点等于
            //k就返回真, 所以要加root != cur
            return true;
        }
        if(root.val < value && search(root.right, cur, value)) {
            return true;
        }
        if(root.val > value && search(root.left, cur, value)) { //root值比value大, 向左找, 向小的地方找, 看上图例子就行, 比如k=9, 
            //其实就在找有没有节点等于value, 比较root与value的值进行向左向右
            return true;
        }
        return false;
    }
    
    //solution2: 用一个HashSet, 对于非BST也有效
    public boolean findTarget(TreeNode root, int k) {
        
        if(root == null) {
            return false;
        }
        
        Set<Integer> set = new HashSet<>();
        return findTargetHelper(root, k, set);
    }
    
    private boolean findTargetHelper(TreeNode root, int k, Set<Integer> set) {
        if(root == null) {
            return false;
        }
        if(set.contains(k - root.val)) {
            return true;
        } else {
            set.add(root.val);
        }
        return findTargetHelper(root.left, k, set) || findTargetHelper(root.right, k, set);
    }
    
    
    //solution3: BST中序遍历得到一个升序数组, 然后双指针前后查有没有等于k的组合
    public boolean findTarget(TreeNode root, int k) {
        
        if(root == null) {
            return false;
        }
        
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        for(int i = 0, j = nums.size() - 1; i < j;) {
            if(nums.get(i) + nums.get(j) == k) {
                return true;
            }
            if(nums.get(i) + nums.get(j) < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
    
    private void inorder(TreeNode root, List<Integer> nums){
        if(root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
}
