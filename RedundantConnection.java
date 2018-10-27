/* In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. 
The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge 
connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that 
occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:

Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:

Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.


Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph 
follow up please see Redundant Connection II). We apologize for any inconvenience caused.*/

class Solution {
    
    //并查集解法
    public int[] findRedundantConnection(int[][] edges) {
        
        if(edges == null || edges.length == 0 || edges[0].length == 0) {
            return new int[2];
        }
        
        int N = edges.length;
        int[] parent = new int[N+1];
        for (int i = 0; i <= N; i++) { //并查集初始化的时候每个元素自己是一个集合
            parent[i] = i; //值都是自己指向自己
        }
        
        int fatherLeft, fatherRight;
        for (int[] now_edge : edges) {       
            fatherLeft = find_father(parent, now_edge[0]);
            fatherRight = find_father(parent, now_edge[1]);
            if (fatherLeft == fatherRight) { //边的两端在同一集合, 那么这条边就是冗余的, 因为这条边的存在而形成了环
                return now_edge; //返回这条边
            }
            else {
                parent[fatherLeft] = fatherRight; //让边左节点指向边右节点, 这就是并查集里面的union操作
            }
        }
        return new int[2]; //没有cycle就返回一个[0,0]
    }
    
    int find_father(int[] parent, int now) { 
        if (now != parent[now]) { //只有自己指向自己的是根节点, 如果不是自己指向自己, 那么他指向的就是自己的父节点
            parent[now] = find_father(parent, parent[now]); //找父节点, 同时进行路径压缩; 路径压缩就是在找到整个集合的根节点之后, 
            //在回朔的同时, 让每一个子节点都直接指向根节点, 这样以后在找根节点的时候只需要一步, 不需要多次递归
        }
        return parent[now]; //返回根节点
    }
    /*1. 并查集的初始化：在最初的时候，根节点都是自己，我们用一个数组parent[i]=i来表示这个关系。
    2. 并查集的查询操作：每次给边的时候判断两个点的祖先节点，我们不停地通过调用parent函数向上寻找直到parent[i]==i
    3. 给出一条边，两个节点设置为l ,r 如果祖先节点father_l, father_r 不相同，说明此时l和r不向连，这条边信息有用（不是一条多余的边），我们就通过
    并查集的合并操作将他们连在一起**。**具体操作需要将祖先节点接在一起，令parent[father_r]=father_l。
    4. 路径压缩优化：在做查询操作的时候我们将parent[now] = find_father(parent[now])，是为了压缩路径，因为一旦两棵树合并，其中一些节点不是直接指
    向根节点的，不合并每次搜索会浪费大量时间
    5. 我们认为总的时间复杂度是O(n)，其中使用了路径压缩的并查集的常数非常小可以忽略
    6. 虽然题目强调如果有多个答案输出最后一条，但用上述方法只会找到一条“多余”的边，所以代码中是从前往后遍历所有边*/
}
