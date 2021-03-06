/*For an integer array (index from 0 to n-1, where n is the size of this array), in the corresponding SegmentTree, 
each node stores an extra attribute max to denote the maximum number in the interval of the array (index from start to end).

Design a query method with three parameters root, start and end, find the maximum number in the interval [start, end] by the given 
root of segment tree.

Example
For array [1, 4, 2, 3], the corresponding Segment Tree is:

                  [0, 3, max=4]
                 /             \
          [0,1,max=4]        [2,3,max=3]
          /         \        /         \
   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
   
query(root, 1, 1), return 4

query(root, 1, 2), return 4

query(root, 2, 3), return 3

query(root, 0, 2), return 4*/

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
    
    //查询区间最大值, 最新写法, 记这个
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || end < root.start || start > root.end) {
            return 0;
        }
        if (root.start == root.end || (start <= root.start && end >= root.end)) { 
            //相当于对于给定区间, 把每个值都走到线段树的最后, 然后从每个节点的max里面挑最大的max.
            //(start <= root.start && end >= root.end)这句话可以提升速度, 如果所要查询的区间比当前节点的区间要宽,
            //那么就不用向下递归了, 因为这个节点已经包含了下面所传上来的最大值, 然后所要查询的区间比当前节点的区间还要广,
            //那么自然就囊括了这个最大值, 直接取这个最大值就ok
            return root.max;
        }
        return Math.max(query(root.left, start, end), query(root.right, start, end));
    }
  
 
    public int query(SegmentTreeNode root, int start, int end) {
        // write your code here
        
        if(root == null || root.start > end || root.end < start || start > end) {
            return Integer.MIN_VALUE; //比如树的区间是[0,10], 所给查询区间是[11,12], 那么就要返回最小值, 没有max
        }
        
        if(root.start > start || root.end < end){
            return query(root, Math.max(root.start, start), Math.min(root.end, end));
            //比如树的区间是[0,10], 所给查询区间是[1,16], 那么就要缩小查询区间到[1,10], 然后再进行查询
        }
        
        if(root.start == start && root.end == end){ //如果左右分别相等就返回, 防止走到叶子节点再判断浪费时间
            return root.max;
        }
        
        if(root.left.end < start){ //左子节点的尾巴比start小就去右边
            return query(root.right, start, end);
        }
        else if(root.right.start > end){ //又子节点的头比end小就去左边
            return query(root.left, start, end);
        }
        
        return Math.max(query(root.left, start, root.left.end), query(root.right, root.right.start, end));
        //所给区间跨越了左右子节点的范围, 就拆分然后分别进行判断, 比如(0,2)拆分成(0,1)与(2,2)进行判断
    }
}
