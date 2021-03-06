/*There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price 
from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.

Note:

    The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
    The size of flights will be in range [0, n * (n - 1) / 2].
    The format of each flight will be (src, dst, price).
    The price of each flight will be in the range [1, 10000].
    k is in the range of [0, n - 1].
    There will not be any duplicated flights or self cycles.
*/

class Solution {
    
    //迪杰斯特拉最短路径算法, Dijkstra's algorithm
    //下面这个代码可以求最短路径, 但是有向图中不能有环
    /*testCase:
    System.out.println(findCheapestPrice(9,new int[][]{{0, 1, 1}, {1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}, {0, 6, 300}, {6, 4, 100}, 
    {0, 7, 350}, {7, 8, 1}, {8, 4, 1}},0,5,3)); //一个比较全面的例子
    System.out.println(findCheapestPrice(4, new int[][]{{0,1,1},{0,2,1},{1,0,1},{2,0,1},{0,3,100}}, 0, 3, 50)); //这个是有环的例子, 这个
    //过不去
    }*/
    //复杂度大体上是O(Elog(E)), E是边数, 因为有一部分路径会重复走; 空间是O(E+V)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if(flights == null || n <= 0 || src < 0 || dst > n) {
            return -1;
        }
        
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>(); //边是有权(价格)的, 所以要map套map
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) {
                prices.put(f[0], new HashMap<>());
            }
            prices.get(f[0]).put(f[1], f[2]); //先扫一遍flights, 存图的关系, 比如0={1=1, 6=300, 7=350}
            //O(E+V)的解释就是0={1=1, 6=300, 7=350}, key就是O(V), value就是O(E), 总共就是O(E+V)
        }
        
        Queue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]); //小到大排序
        pq.add(new int[] {0, src, k + 1}); //这里是k + 1
        //boolean[] visited = new boolean[n]; 
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) { //先判断到没到目标地点, 然后再去往后走
                return price;
            }
            //if(visited[city]) continue;
            //visited[city] = true;
            if (stops > 0 && prices.containsKey(city)) { //这个地方要判断prices存不存在, 因为有向图中有的点是终点, 没有任何出度, 
                //所以统计prices的时候是不会加入这些终端点的, 但是这些终端点会被加入到队列, 所以弹出之后要判断prices存不存在
                Map<Integer, Integer> adj = prices.get(city); //拿出city的所有邻接点
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1}); //每一个邻接点的可能性和price结果, 放入队列
                }
            }
        }
        return -1;
    }
    /*不能加visit的原因: testCase: 
    4
    [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
    0
    3
    1
    因为这道题限制了k stops, 所以按照这个testCase, 会走到2(这个2的剩余步数为0), 这个时候因为把2标记为visited了, 那么这个2就不会把3加入到队列,
    后面的2剩余步数1的就无法访问, 就无法走到3, 所以这个visited适合不限制中途能走多少步, 只要能够走到终点的那种最短路径题目*/
}
