/*On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0
 

Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000*/

class Solution {
    
    //O(n^2)正常dfs
    /*思路: 如果两个石头在同行或者同列, 两个石头就是连接的. 连在一起的石头, 可以组成一个连通图. 每一个连通图至少会剩下1个石头.
    所以我们希望存在一种思路, 每个连通图都只剩下1个石头.
    这样这题就转化成了数岛屿的问题. 
    
    Connected stones can be reduced to 1 stone,
    the maximum stones can be removed = stones number - islands number.
    so just count the number of "islands".*/
    public int removeStones(int[][] stones) {
        if(stones == null || stones.length == 0 || stones[0].length == 0) {
            return 0;
        }
        
        Set<int[]> visited = new HashSet<>();
        int connected = 0;
        for(int[] stone : stones) {
            if(!visited.contains(stone)) {
                connected++; //统计连通图的个数, 每个图最后只剩下一个stone, 所以图的个数就是最后要剩的石头数
                helper(stone, stones, visited);
            }
        }
        
        return stones.length - connected; //石头数减去图的个数也就是需要移除的石头数
    }
    
    public void helper(int[] stone, int[][] stones, Set<int[]> visited) {
        if(!visited.contains(stone)) {
            visited.add(stone);
            for(int[] step : stones) { //每一次都要从头扫, 这也就是O(n^2)的原因
                if(step[0] == stone[0] || step[1] == stone[1]) { //因为判断连通的条件是行坐标或者纵坐标相等, 所以这么判断
                    helper(step, stones, visited);
                }
            }
        }
    }
 
    
    //并查集解法
    Map<Integer, Integer> f = new HashMap<>();
    int islands = 0;

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; ++i)
            union(stones[i][0], ~stones[i][1]);
        return stones.length - islands;
    }

    public int find(int x) {
        if (f.putIfAbsent(x, x) == null)
            islands++;
        if (x != f.get(x))
            f.put(x, find(f.get(x)));
        return f.get(x);
    }

    public void union(int x, int y) {
        System.out.println(x + " " + y);
        x = find(x);
        y = find(y);
        if (x != y) {
            f.put(x, y);
            islands--;
        }
    }
}
