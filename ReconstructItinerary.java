/*Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.*/

class Solution {
    
    //testCase: 对应discuss的图: 
    //String[][] tickets = {{"JFK","A"},{"JFK","D"},{"A","C"},{"C","D"},{"C","JFK"},{"B","C"},{"D","B"},{"D","A"}};
    
    /*首先从起点JFK出发, dfs找到一个sub-path: JFK->A->C->D->A, 在A处出现dead end(不再有可以走的边), 此时将A加到解当中, dfs返回. 对于返回到的节
    点D, 还有可以继续走的subpath, dfs继续找, 得：D->B->C->JFK->D. 此时的D为dead end, 说明可以将D加到解当中, 而且处于已经加过的点之前. 以此类推, 
    每次都加dead end的节点. 直到所有点都是dead end!*/
    
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> path;
    
    public List<String> findItinerary(String[][] tickets) {
        flights = new HashMap<>();
        path = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>()); //用优先级队列存地点保证lexical order
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }
    
    private void dfs(String departure) { //就是从JFK出发找环, 找到第一个环的dead end就是最后一个点, 然后回退到上一个点继续找环, 
        //遇到的dead end作为倒数第二个点, 依次进行下去
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty()) {
            dfs(arrivals.poll());
        }
        path.addFirst(departure);
    }
}
