/*Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers 
[start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

Example
For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]

Challenge
O(logN) time for each query*/

public class Solution {
    
    //线段树每次查询O(logN)方法:
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if(A == null || A.length == 0 || queries == null || queries.size() == 0) {
            return result;
        }
        
        SegmentTreeNode root = build(0, A.length - 1, A);
        for(Interval inter : queries) {
            result.add(query(root, inter.start, inter.end));
        }
        
        return result;
    }
    
    public SegmentTreeNode build(int start, int end, int[] A) { //一模一样的方法
        if(start == end) {
            return new SegmentTreeNode(start, end, A[start]); //A[start]就最大了
        }
        
        SegmentTreeNode left = build(start, (start + end) / 2, A);
        SegmentTreeNode right = build((start + end) / 2 + 1, end, A);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Integer.MAX_VALUE);
        root.left = left;
        root.right = right;
        root.min = Math.min(left.min, right.min); //挑左子树和右子树的最大值就行
        return root;
    }
        
    public int query(SegmentTreeNode root, int start, int end) { //一模一样的方法
        if (root == null || end < root.start || start > root.end) {
            return Integer.MAX_VALUE;
        }
        if (root.start == root.end || (start <= root.start && end >= root.end)) {
            //(start <= root.start && end >= root.end)这句话可以提升速度, 如果所要查询的区间比当前节点的区间要宽,
            //那么就不用向下递归了, 因为这个节点已经包含了下面所传上来的最小值, 然后所要查询的区间比当前节点的区间还要广,
            //那么自然就囊括了这个最小值, 直接取这个最小值就ok
            return root.min;
        }
        return Math.min(query(root.left, start, end), query(root.right, start, end));
    }
    
    class SegmentTreeNode {
        public int start, end, min;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int min) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.left = this.right = null;
        }
    }
    
    
    //原始做法, O(mn)
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        if(A == null || A.length == 0) {
            return new ArrayList<>();
        }
        
        List<Integer> result = new ArrayList<>();
        for(Interval inter : queries) {
            int start = inter.start;
            int end = inter.end;
            int min = Integer.MAX_VALUE;
            for(int i = start; i <= end; i++) {
                min = Math.min(min, A[i]);
            }
            result.add(min);
        }
        
        return result;
    }
}
