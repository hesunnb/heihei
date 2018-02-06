/*Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance 
between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] 
(inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]*/


class Solution {
    
    //On^2
    public int numberOfBoomerangs(int[][] points) {
        if(points == null) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points.length; j++) {
                if(i == j) { //相同的点不计算距离
                    continue;
                }
                
                int d = getDistance(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1); //同一个距离有多少个点
            }
            
            for(int val : map.values()) {
                result += val * (val-1); //比如距离为3的有5个点, 然后就A52(顺序有关)计算, 5*4; C52(顺序无关), (5*4)/(2*1)
            }
            map.clear();
        }
        return result;
    }
    
    private int getDistance(int[] a, int[] b) {
        int dx = b[0] - a[0];
        int dy = b[1] - a[1];
        
        return dx*dx + dy*dy;
    }
}
