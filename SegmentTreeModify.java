/*For a Maximum Segment Tree, which each node has an extra value max to store the maximum value in this node's interval.

Implement a modify function with three parameter root, index and value to change the node's value with [start, end] = [index, index] 
to the new given value. Make sure after this change, every node in segment tree still has the max attribute with the correct value.

Example
For segment tree:

                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]
if call modify(root, 2, 4), we can get:

                      [1, 4, max=4]
                    /                \
        [1, 2, max=4]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=4], [3, 3, max=0], [4, 4, max=3]
or call modify(root, 4, 0), we can get:

                      [1, 4, max=2]
                    /                \
        [1, 2, max=2]                [3, 4, max=0]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=0]*/

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
     *@param root, index, value: The root of segment tree and 
     *@ change the node's value with [index, index] to the new given value
     *@return: void
     */
     
    //时间: Do it in O(h) time, h is the height of the segment tree.
    public void modify(SegmentTreeNode root, int index, int value) {
        // write your code here
        
        if(root == null || root.start > index || root.end < index) {
            return;
        }
        
        if(root.start == index && root.end == index) {
            root.max = value; //更改值
            return;
        }
        
        if(root.right.start > index){ //往左去
            modify(root.left, index, value);
        } 
        else if(root.left.end < index){ //往右去
            modify(root.right, index, value);
        }
        
        root.max = Math.max(root.left.max, root.right.max); //更新值
    }
}
