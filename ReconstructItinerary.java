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
    /*首先从起点'JFK出发， d旬捆←斗sub-psth: JFK->.岛叫:->D->A，在A处出现d回d
    end何有宿可以是自铀)， JI:tIIIJ将叫回鹏当申. d伽遁回.对于洒回到的节点D. 还有可
    以继辑走的subpa由， d阳缠绵拢，得: D->B->C-晶'JFK->D. 此时的D为d髓de时，说明
    可以将D1I圄鹏当中，而且处于自副阻四点之前.u提雄，每次翻都归国d end的节
    点.噩'加南点都翩翩d endl*/
    public List<String> findItinerary(String[][] tickets) {
        
    }
}
