/*Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note: 
You may assume k is always valid, 1 ≤ k ≤ n方.*/

class Solution {
    
    //solution2: 开空间, 优先级队列
    /*1. Build a minHeap of elements from the first row.
    2. Do the following operations k-1 times :
    Every time when you poll out the root(Top Element in Heap), you need to know the row number and column number of that element
    (so we can create a tuple class here), replace that root with the next element from the same column.*/
    //类似题leetcode373, 复杂度klog(n)
    public int kthSmallest(int[][] matrix, int k) {
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }
        
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j < n; j++) { //此处加入j < n && j < k也可, 但是意义不大, 因为是个matrix, 所以要找的k基本都会比n大, k要是比n小的话, 
            //弹出的次数也不会很多
            pq.offer(new Tuple(0, j, matrix[0][j])); //把第一行加到队列中, 主要意图就是怕比如, 某个元素向下(列)的值比它向右(行)的值小, 
            //这时候就该取列方向上的值, 用优先级队列, 就是说每个元素在列方向上, 向下找的同时, 会自动和行方向上的值进行比较
        }
        for(int i = 0; i < k-1; i++) { //做k-1次弹出
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val; //第k个值
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}
