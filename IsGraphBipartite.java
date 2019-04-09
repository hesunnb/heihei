/*Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the 
graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node 
is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't 
contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].*/

class Solution {
    
    /*[[1,3], [0,2], [1,3], [0,2],[],[],[],[],[],[],[]]这种例子返回的也是真, 因为这些[]加在后面没有影响图的连接, 但是如果加在中间或者前面, 
    比如[[1,3], [0,2],[], [1,3], [0,2]] 或者 [[],[1,3], [0,2], [1,3], [0,2]] 都会影响图的连接, 像放在前面这种就是0节点什么都没连, 1节点连接
    了[1,3], 就产生错误了; 但是[[1,4], [0,2], [1,4], [], [0,2]]这个图返回的是真, 这是一个非连接图(disconnected graph), 3这个节点跟任何一个点
    都没有连接, 但为什么是真呢, 原因是题中说every edge in the graph has one node in A and another node in B, 所以这是从边入手的, 3没有和任何
    一个点相连, 所以没有边, 那么存在的边都符合Recall that a graph is bipartite if we can split it's set of nodes into two independent 
    subsets A and B such that every edge in the graph has one node in A and another node in B. 这个条件, 所以这个例子成立, 
    也就是说disconnected graph也可以为真.
    
    只有[[]]的例子也是真*/
    
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];			
        /*为什么要检查每一个没有访问的节点: 
        比如这个例子[[],[2,4,6],[1,4,8,9],[7,8],[1,2,8,9],[6,9],[1,5,7,8,9],[3,6,9],[2,3,4,6,9],[2,4,5,6,7,8]] 0节点和任何点都没有相连
        也是非连接图, 但是是对的, 因为0没有和任何节点产生边, 自己也没有环, 所以没问题, 那么如果只从graph[0]开始递归, 就会返回真, 但是实际上后面
        这些点组成的图会返回假, 所以除了0还要接着向后扫, 扫描所有没有访问过的节点*/
        for (int i = 0; i < n; i++) { //check each unvisited node. 
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) { //colors[i] == 0就是没有访问过的点
                return false;
            }
        }
        return true;
    }
    
    public boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }       
        colors[node] = color;       
        for (int next : graph[node]) { //每一个点的邻接点
            if (!validColor(graph, colors, -color, next)) { //给出相反颜色
                return false;
            }
        }
        return true;
    }
    /*思路: Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
    Initialize a color[] array for each node. Here are three states for colors[] array:
    0: Haven't been colored yet.
    1: Blue.
    -1: Red.
    For each node,

    If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
    If it has been colored, check if the current color is the same as the color that is going to be used to color it.*/
}
