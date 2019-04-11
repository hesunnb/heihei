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
        boolean[] visited = new boolean[N + 1];
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
            result = curDist;
            count++;
            if(map.containsKey(curNode)) {
                Map<Integer, Integer> adj = map.get(curNode);
                for(int a : adj.keySet()) {
                    pq.offer(new int[] {curDist + adj.get(a), a});
                }
            }
        }
        return count == N ? result : -1;
    }
}
