/*Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.*/

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
   
    //solution1: 中序遍历的同时直接找出最小值
    TreeNode prev; //要用全局前一个节点的原因就是, 你在一个栈中改变了prev的值, 指向了别的节点, 但是你并没有返回它, 回到别的节点自己的栈中它自己
    //的prev并没有改变, 所以就不对了; 如果非要带着prev传的话, 可以把它放到一个ArrayList<TreeNode>里面, 然后带着ArrayList<TreeNode>传就行了
    public int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        
        int min = Integer.MAX_VALUE;
        return inorder(root, min);
    }
    
    private int inorder(TreeNode root, int min) { 
        if(root == null) {
            return min;
        }
        
        min = inorder(root.left, min); //min值可以照传不误, 因为min值是返回的, 注意一定要接收传回来的min值
        if(prev != null) {
            min = Math.min(min, root.val - prev.val); //因为中序遍历BST是升序, 所以当前节点root的值一定比prev节点的值要大, 所以直接减就行了, 
            //不用加绝对值
        }
        prev = root;
        
        min = inorder(root.right, min); //接收传回来的min值
        return min;
    }
   
    
    //solution2: Make use of the property of BST that value of nodes is bounded by their "previous" and "next" node.
    long minDiff = Long.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        helper(root,Long.MIN_VALUE,Long.MAX_VALUE);
        return (int)minDiff;
    }
    
    private void helper(TreeNode curr, long lb, long rb){
        if(curr==null) return;
        if(lb!=Long.MIN_VALUE){
            minDiff = Math.min(minDiff,curr.val - lb);
        }
        if(rb!=Long.MAX_VALUE){
            minDiff = Math.min(minDiff,rb - curr.val);
        }
        helper(curr.left,lb,curr.val);
        helper(curr.right,curr.val,rb);
    }
   
   
    //solution3: follow up, 如果输入的树不是bst怎么办
    /*What if it is not a BST? (Follow up of the problem) The idea is to put values in a TreeSet and then every time we can use O(lgn) 
    time to lookup for the nearest values. Time complexity O(nlgn), space complexity O(n).*/
    public int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        
        TreeSet<Integer> set = new TreeSet<>();
        int min = Integer.MAX_VALUE;
        return getMinimumDifferenceHelper(root, set, min);
    }
    
    private int getMinimumDifferenceHelper(TreeNode root, TreeSet<Integer> set, int min) {
        if (root == null) {
            return min;
        }
        
        //TreeSet用floor, ceiling查询元素用O(lgn)的时间, TreeSet加入元素的时候自动排序
        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) { //这个不等于null要加, 因为如果root.val比set中所有元素都要小的话, set就没有符合条件的结果, 
               //此时就会返回null；floor找比root.val刚刚小一个的数
                min = Math.min(min, root.val - set.floor(root.val)); 
            }
            if (set.ceiling(root.val) != null) { //同理, ceiling找比root.val刚刚大一个的数
                min = Math.min(min, set.ceiling(root.val) - root.val);
            }
        }
        set.add(root.val);
        
        min = getMinimumDifferenceHelper(root.left, set, min);
        min = getMinimumDifferenceHelper(root.right, set, min);
        return min;
    }
   
   
    //solution4: (own)中序遍历一遍放到ArrayList里面, 然后扫一遍求最小值即可
    public int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int result = Integer.MAX_VALUE;
        
        for(int i = 0; i < nums.size() - 1; i++) {
            result = Math.min(result, Math.abs(nums.get(i) - nums.get(i+1)));
        }
        return result;
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
