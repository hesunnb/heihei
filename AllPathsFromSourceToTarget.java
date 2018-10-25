/*Given a directed, acyclic graph(无环图) of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) 
exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.*/

class Solution {

    //就是考dfs
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(graph == null || graph.length == 0 || graph[0].length == 0) {
            return result;
        }
        
        for(int i = 0; i < graph[0].length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(0); //加入起始点
            list.add(graph[0][i]); //加入起始点对应数组的第i个
            allPathsSourceTargetHelper(graph, list, result, graph[0][i]);
        }
        return result;
    }
    
    private void allPathsSourceTargetHelper(int[][] graph, List<Integer> list, List<List<Integer>> result, int index) {
        
        if(index == graph.length - 1) { //到末尾了就加入到result
            result.add(new ArrayList(list));
            return;
        }
        
        for(int i = 0; i < graph[index].length; i++) { //进行递归搜索
            list.add(graph[index][i]);
            allPathsSourceTargetHelper(graph, list, result, graph[index][i]);
            list.remove(list.size() - 1);
        }
    }
}
