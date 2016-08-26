/*Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example
Given 4 points: (1,2), (3,6), (0,0), (1,3).

The maximum number is 3.*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param points an array of point
     * @return an integer
     */
     
    //时间O(n^2), 当前点和其余所有点做直线, 那么有一个斜率上点最多, 那么这个斜率就已经是最多的点了, 比如从下一个点开始, 如果还是这个斜率的话, 那么所能得出的点一定比前面那个点得出的少(因为它只跟后面的点作比较), 所以就得看其他斜率了
    //这个方法就是所有的每两个点都做了直线, 获得了所有的组合, 相同斜率的都并到了一起
    public int maxPoints(Point[] points) {
        // Write your code here
        
        //double的值还是很稳定的, 比如a=1.0/3.0,b=1.0/3.0,那么a与b一定相等, double的问题是判断大小的时候不一定准, 但是double
        //自己算出来的数是很稳定的
        
        if(points == null || points.length == 0) {
            return 0;
        }
        
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        int max = 1; //points只有一个点直接返回max了
        for(int i = 0; i < points.length; i++) { //以每一个点为基准, 和它后面所有的点求斜率, 找出最大的max
            // shared point changed, map should be cleared and server the new point
            map.clear();
            
            // maybe all points contained in the list are same points,and same points' k is 
            // represented by Integer.MIN_VALUE
            map.put((double)Integer.MIN_VALUE, 1); //全都是相同的点的话, 就放在斜率最小的地方, 
            //其实这个(double)Integer.MIN_VALUE可以是其它的任何值
            int dup = 0;
            for(int j = i + 1; j < points.length; j++) {
                if(points[j].x == points[i].x && points[j].y == points[i].y) {
                    dup++;
                    continue;
                }
                
                // look 0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x)
                // because (double)0/-1 is -0.0, so we should use 0.0+-0.0=0.0 to solve 0.0 !=-0.0
                // problem

                // if the line through two points are parallel to y coordinator, then K(slop) is 
                // Integer.MAX_VALUE
                double key = points[j].x - points[i].x == 0 ? (double)Integer.MAX_VALUE : 0.0 + (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x); //java当中有正0和负0一说
                
                if(map.containsKey(key)) {
                    map.put(key, map.get(key) + 1); //有值就+1
                } else {
                    map.put(key, 2); //没有的话就让值为2(表示一个新的斜率然后有两个点)
                }
            }
            
            for(int temp : map.values()) {
                max = Math.max(max, temp + dup); //dup是和基准点一样的点, 比如现在的基准点是(1,1), 后面的那些点中还有
                //3个(1,1), 那么dup就会是3, 然后1+3=4个点
            }
        }
        
        return max;
    }
}
