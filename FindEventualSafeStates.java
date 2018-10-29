/*In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal 
(that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a 
natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: 
graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

    graph will have length at most 10000.
    The number of edges in the graph will not exceed 32000.
    Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
*/

class Solution {
    /*value of color represents three states:
    0:have not been visited
    1:safe
    2:unsafe
    For DFS,we need to do some optimization.When we travel a path,we mark the node with 2 which represents having been visited,and 
    when we encounter a node which results in a cycle,we return false,all node in the path stays 2 and it represents unsafe.And in 
    the following traveling,whenever we encounter a node which points to a node marked with 2,we know it will results in a cycle,so 
    we can stop traveling.On the contrary,when a node is safe,we can mark it with 1 and whenever we encounter a safe node,we know it 
    will not results in a cycle.*/
    public List<Integer> eventualSafeNodes(int[][] graph) {
        
        List<Integer> result = new ArrayList<>();
        if(graph == null || graph.length == 0) {
            return result;
        }
        
        int[] color = new int[graph.length];
        
        for (int i = 0; i < graph.length; i++) { //要求返回的result是排序的, 正好按照节点大小扫描, 所以自动排序了
            if(dfs(graph, color, i)) {
                result.add(i);
            }
        }
        return result;
    }
    
    private boolean dfs(int[][] graph, int[] color, int index) {
        if(color[index] != 0) { //访问过的点就得标记上, 不能再清回0了, 因为清0再从新赋值会增加递归次数, 会超时
            return color[index] == 1; //不是0的时候就看是不是1了, 不是1那么就是有环的点
        }
        
        color[index] = 2; //默认给的是2
        for(int i : graph[index]){
            if(!dfs(graph, color, i)) {
                return false;
            }
        }
        color[index] = 1; //如果一个节点没有neighbors, 或者这个从这个节点出发的所有路径都没有环(上面的for循环全部执行完毕), 
        //那么就说明这个点是安全的
        
        return true;
    }
}    
