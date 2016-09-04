/*Example
Given [3,2,1,4]. The segment tree will be:

                 [0,  3] (max = 4)
                  /            \
        [0,  1] (max = 3)     [2, 3]  (max = 4)
        /        \               /             \
[0, 0](max = 3)  [1, 1](max = 2)[2, 2](max = 1) [3, 3] (max = 4)

跟一问的区别就是这里start和end成了下标, 多了一个max*/

/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, max;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int max) {
 *         this.start = start;
 *         this.end = end;
 *         this.max = max
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     *@param A: a list of integer
     *@return: The root of Segment Tree
     */
    public SegmentTreeNode build(int[] A) {
        // write your code here
        if(A == null || A.length == 0) {
            return null;
        }
        
        int start = 0;
        int end = A.length - 1;

        return helper(start, end, A);
    }
    
    private SegmentTreeNode helper(int start, int end, int[] A) {
        if(start == end) {
            return new SegmentTreeNode(start, end, A[start]); //A[start]就最大了
        }
        
        SegmentTreeNode left = helper(start, (start + end) / 2, A);
        SegmentTreeNode right = helper((start + end) / 2 + 1, end, A);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Integer.MIN_VALUE);
        root.left = left;
        root.right = right;
        root.max = Math.max(left.max, right.max); //挑左子树和右子树的最大值就行
        return root;
    }
}
