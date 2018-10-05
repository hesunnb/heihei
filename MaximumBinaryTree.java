/*Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].*/

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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        return constructMaximumBinaryTreeHelper(0, nums.length - 1, nums);
    }
    
    private TreeNode constructMaximumBinaryTreeHelper(int start, int end, int[] nums) {
        int index = findMaximumNumber(start, end, nums);
        TreeNode root = new TreeNode(nums[index]);
        
        if(start <= index-1) {
            root.left = constructMaximumBinaryTreeHelper(start, index-1, nums);
        }
        if(end >= index+1) {
            root.right = constructMaximumBinaryTreeHelper(index+1, end, nums);
        }
        return root;
    }
    
    //题目中说了没有重复no duplicates
    private int findMaximumNumber(int start, int end, int[] nums) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i = start; i <= end; i++) {
            if(nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
      
    
    //version2:
    //O(n)用栈
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return null;
        }

        //比如4,2,1,3,0,5 用这个例子就能看出这个栈是怎么工作的
        //4,2,1,5,0,3
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0; i < nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && stack.peek().val < nums[i]) { //如果栈顶元素比来的值小, 那么就从栈顶元素开始拿, 然后放到来的值的左子树
                curr.left = stack.pop();
            }
            if(!stack.isEmpty()) { //然后小的都拿完了就会遇到大的, 连接到大的值的右子树
                stack.peek().right = curr;
            }
            stack.push(curr); //把当前节点放入栈, 待比较
        }
        
        TreeNode result = null;
        while(!stack.isEmpty()) {
            result = stack.pop(); //最后在栈底的就是根节点
        }
        return result;
    }
}
