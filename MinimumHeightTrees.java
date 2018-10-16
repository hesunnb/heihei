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

    //最蠢的dfs...暴力解
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
