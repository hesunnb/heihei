/*
Given a sorted (increasing order) array, Convert it to create a binary tree with minimal height.
Notice

There may exist multiple valid solutions, return any of them.

Example

Given [1,2,3,4,5,6,7], return

     4
   /   \
  2     6
 / \    / \
1   3  5   7
*/
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */ 

public class Solution {
    /**
     * @param A: an integer array
     * @return: a tree node
     */
    public TreeNode sortedArrayToBST(int[] A) {  
        // write your code here
        return buildTree(A, 0, A.length - 1);
    }  
    
    private TreeNode buildTree(int[] A, int start, int end) {
        int mid = 0;
        if(start <= end) { //带等号, 走到叶子节点的时候是start == end的时候
            mid = start + (end - start) / 2; //每次都找sortedArray的中点
        } else {
            return null; //start > end就到了叶子节点的下面, 所以返回null
        }
        
        TreeNode root = new TreeNode(A[mid]); //把中点转成节点
        root.left = buildTree(A, start, mid - 1); //找中点左边数组的中点
        root.right = buildTree(A, mid + 1, end); //找中点右边数组的中点
        return root;
    }
}
