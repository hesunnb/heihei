/*We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], 
this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number 
of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.*/

class Solution {
    
    //testCase: [[1, 2, 7], [3, 6, 7], [3, 4]], 从7到4
    //routes的下标号是bus号码, 里面的值是站, bus号和站号是不同的
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(routes == null || routes.length == 0 || routes[0].length == 0) {
            return -1;
        }
        
        if(S == T) {
            return 0;
        }
        
        //存站号与bus号之间的关系, 用map套ArrayList来存
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>(); //用来存访问过的bus
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < routes.length; i++) {
            for(int j = 0; j < routes[i].length; j++) {
                List<Integer> busList = map.getOrDefault(routes[i][j], new ArrayList<>());
                busList.add(i); //行数就是bus
                map.put(routes[i][j], busList);
            }
        }
        queue.offer(S);
        
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++; //注意这道题level在这里++, 一旦进入循环就说明上车了, 所以直接就++
            for(int i = 0; i < size; i++) {
                int cur = queue.poll(); //弹出当前站
                for(int bus : map.get(cur)) { //得到当前站的bus号
                    if(visited.contains(bus)) {
                        continue;
                    }
                    visited.add(bus);
                    for(int j = 0; j < routes[bus].length; j++) {
                        if(routes[bus][j] == T) { //当前bus所走的站有没有终点
                            return level;
                        }
                        if(routes[bus][j] != cur) { //没有的话加入除自己以外的所有站号
                            queue.offer(routes[bus][j]);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
