/*For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in 
the the array which value is between interval start and end. (The array may not fully filled by elements)

Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] 
by the given root of value SegmentTree.

Example
For array [0, 2, 3], the corresponding value Segment Tree is:

                     [0, 3, count=3]
                     /             \
          [0,1,count=1]             [2,3,count=2]
          /         \               /            \
   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
   
query(1, 1), return 0

query(1, 2), return 1

query(2, 3), return 2

query(0, 2), return 2*/

/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, count;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int count) {
 *         this.start = start;
 *         this.end = end;
 *         this.count = count;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        if(root == null || root.start > end || root.end < start || start > end) {
            return 0; //比如树的区间是[0,10], 所给查询区间是[11,12], 那么就要返回0, 没有count
        }
        
        if(root.start > start || root.end < end){
            return query(root, Math.max(root.start, start), Math.min(root.end, end));
            //比如树的区间是[0,10], 所给查询区间是[1,16], 那么就要缩小查询区间到[1,10], 然后再进行查询
        }
      
        if(root.start == start && root.end == end){ //如果左右分别相等就返回, 防止走到叶子节点再判断浪费时间
            return root.count;
        }
        
        if(root.left.end < start){ //左子节点的尾巴比start小就去右边
            return query(root.right, start, end);
        }
        else if(root.right.start > end){ //又子节点的头比end小就去左边
            return query(root.left, start, end);
        }
        
        return query(root.left, start, root.left.end) + query(root.right, root.right.start, end);
        //所给区间跨越了左右子节点的范围, 就拆分然后分别进行判断, 比如(0,2)拆分成(0,1)与(2,2)进行判断
    }
}
