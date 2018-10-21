/*For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all 
possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all 
the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is 
a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will 
not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]

Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]

Note:

    According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly 
    one path. In other words, any connected graph without simple cycles is a tree.”
    The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/

class Solution {

        
    //solution2: 类似拓扑排序的bfs
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if(n <= 1 || edges == null) {
            return Collections.singletonList(0);
        }

        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) { //这道题The graph contains n nodes which are labeled from 0 to n - 1, 所以这里用下标默认替代了label值
            adj.add(new HashSet<>());
        }
        for (int[] edge : edges) { //把无向图加入到adj里面
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) { //有向图是把入度放入哈希表(拓扑排序), 那么无向图因为可以互相指, 所以neighbours为1个的就是最外围的一圈
                leaves.add(i);
            }
        }
        
        while (n > 2) {
            n -= leaves.size(); //把最外围的一圈减下去
            List<Integer> newLeaves = new ArrayList<>();
            for (int i : leaves) {
                int j = adj.get(i).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size() == 1) {
                    newLeaves.add(j); //把新的最外围的一圈加进来
                }
            }
            leaves = newLeaves; //更新
        }
        return leaves; //最后剩下一个或者是两个节点就是最后的结果
    }
    /*思路:Our problem want us to find the minimum height trees and return their root labels. First we can think about a simple case -- a
    path graph.

    For a path graph of n nodes, find the minimum height trees is trivial. Just designate the middle point(s) as roots.

    Despite its triviality, let design a algorithm to find them.

    Suppose we don't know n, nor do we have random access of the nodes. We have to traversal. It is very easy to get the idea of two 
    pointers. One from each end and move at the same speed. When they meet or they are one step away, (depends on the parity of n), we 
    have the roots we want.

    This gives us a lot of useful ideas to crack our real problem.

    For a tree we can do some thing similar. We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the pointers
    move the same speed. When two pointers meet, we keep only one of them, until the last two pointers meet or one step away we then find 
    the roots.

    It is easy to see that the last two pointers are from the two ends of the longest path in the graph.

    The actual implementation is similar to the BFS topological sort. Remove the leaves, update the degrees of inner vertexes. Then remove
    the new leaves. Doing so level by level until there are 2 or 1 nodes left. What's left is our answer!

    The time complexity and space complexity are both O(n).

    Note that for a tree we always have V = n(顶点为n), E = n-1(边数为n-1).*/
        
        
    //solution3: 最蠢的dfs...暴力解
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> result = new ArrayList<>();
        if(n <= 0 || edges == null) {
            return result;
        }
        if(n == 1) {
            result.add(0);
            return result;
        }

        Map<Integer, List<Integer>> graphPath = new HashMap<>();
        for(int i = 0; i < edges.length; i++) {
            if(!graphPath.containsKey(edges[i][0])) {
                List<Integer> list = new ArrayList<>();
                list.add(edges[i][1]);
                graphPath.put(edges[i][0], list);
            } else {
                List<Integer> list = graphPath.get(edges[i][0]);
                list.add(edges[i][1]);
                graphPath.put(edges[i][0], list);
            }

            if(!graphPath.containsKey(edges[i][1])) {
                List<Integer> list = new ArrayList<>();
                list.add(edges[i][0]);
                graphPath.put(edges[i][1], list);
            } else {
                List<Integer> list = graphPath.get(edges[i][1]);
                list.add(edges[i][0]);
                graphPath.put(edges[i][1], list);
            }
        }
        
        List<Integer> lengthList = new ArrayList<>();
        int totalMin = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            List<Integer> pathList = new ArrayList<>();
            pathList.add(i);
            int levelMin = dfs(i, pathList, graphPath, Integer.MIN_VALUE);

            if(totalMin > levelMin) {
                totalMin = levelMin;
            }
            lengthList.add(levelMin);
        }

        for(int i = 0; i < lengthList.size(); i++) {
            if(lengthList.get(i) == totalMin) {
                result.add(i);
            }
        }

        return result;
    }

    private int dfs(int i, List<Integer>pathList, Map<Integer, List<Integer>> graphPath, int levelMin) {
        for(int node : graphPath.get(i)) {
            if(pathList.contains(node)) {
                continue;
            }
            pathList.add(node);
            levelMin = dfs(node, pathList, graphPath,levelMin);
            pathList.remove(pathList.size() - 1);
        }
        if(levelMin < pathList.size() - 1) {
            levelMin = pathList.size() - 1;
        }
        return levelMin;
    }
}
