/*There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is 
the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.*/

class Solution {

    //就是考迪杰斯特拉
    public int networkDelayTime(int[][] times, int N, int K) {
        if(times == null || times.length == 0 || N < 1 || K > N) {
            return -1;
        }
        
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] time : times) {
            if(!map.containsKey(time[0])) {
                map.put(time[0], new HashMap<>());
            }
            map.get(time[0]).put(time[1], time[2]);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        pq.offer(new int[] {0, K});
        boolean[] visited = new boolean[N + 1]; //这个就是有一个visited数组, 每个点只要走一次, 因为这道题并没有中间步数的限制
        //所以一旦走到一个点就必然是最小距离(因为优先级队列筛选), 并且也会向队列加入这个点的邻接点(有步数限制的因为没有步数从而不会加入这个点
        //的邻接点)
        int result = -1;
        int count = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0];
            int curNode = cur[1];
            if(visited[curNode]) {
                continue;
            }
            visited[curNode] = true;
            result = curDist; //每弹出一个点就更新一下result, 因为pq按照dist排序, 所以最后一个弹出的有效点就是整个网络最后能够到达的点
            count++;
            if(map.containsKey(curNode)) {
                Map<Integer, Integer> adj = map.get(curNode);
                for(int a : adj.keySet()) {
                    pq.offer(new int[] {curDist + adj.get(a), a});
                }
            }
        }
        return count == N ? result : -1; //因为每个点只访问一次, 所以队列弹出的点就是N
    }
}
