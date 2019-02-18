/*We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000*/

class Solution {
    public int[][] kClosest(int[][] points, int K) {

        if(points == null || points.length == 0 || points[0].length == 0 || K > points.length) {
            return new int[][]{};
        }

        int[][] result = new int[K][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new kClosestComparator());
        for(int[] i : points) {
            pq.offer(i);
        }

        for(int i = 0; i < K; i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    class kClosestComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] * o1[0] + o1[1] * o1[1] - o2[0] * o2[0] - o2[1] * o2[1];
        }
    }
}


/*lintCode版本, 要求更多
612. K Closest Points
Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, 
sorted by y-axis.

Example
Example 1:

Input: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
Output: [[1,1],[2,5],[4,4]]
Example 2:

Input: points = [[0,0],[0,9]], origin = [3, 1], k = 1
Output: [[0,0]]*/

public class Solution {
 
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        if(points == null || points.length == 0 || k > points.length) {
            return new Point[]{};
        }

        Point[] result = new Point[k];
        PriorityQueue<Point> pq = new PriorityQueue<>(new kClosestComparator(origin)); //新的点是Comparator也能传参数进来
        for(Point i : points) {
            pq.offer(i);
        }

        for(int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    class kClosestComparator implements Comparator<Point> {

        private Point origin;
        kClosestComparator(Point origin) { //用构造函数来接收Comparator传进来的参数
            this.origin = origin;
        }
        @Override
        public int compare(Point o1, Point o2) {
            int result = (o1.x - origin.x) * (o1.x - origin.x) + (o1.y - origin.y) * (o1.y - origin.y) - 
                         (o2.x - origin.x) * (o2.x - origin.x) - (o2.y - origin.y) * (o2.y - origin.y);
            if(result == 0) {
                if(o1.x == o2.x) {
                    result = o1.y - o2.y;
                } else {
                    result = o1.x - o2.x;
                }
            }
            return result;
        }
    }
}
