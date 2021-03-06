/*Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a label (int) and a list 
(List[UndirectedGraphNode]) of its neighbors. There is an edge between the given node and each of the nodes in its neighbors.


OJ's undirected graph serialization (so you can understand error output):
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 

Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. 
You don't need to understand the serialization to solve the problem.*/

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
     
    //用BFS做(通过一个点找到其它所有的点)，与二叉树的不同，除啦队列，还需要用到hashmap，作用是存储访问过的点，因为图中访问到每个结点时会重复访问
    //已经访问过的结点，这是不必要的，就用hashmap给屏蔽掉，访问过就不再访问啦，相反二叉树的点不会重复，所以不用hashmap
    
    //这个图是有规律的，neighbors不是随意的，比如从0开始，0就有1和2是相邻点，1的neighbor只有2，因为和0连过啦，而2只能连自己，因为和0,1都连过啦，
    //就算neighbors的里面
     
    //因为nodes里面的点不能像二叉树BFS那样从队列里面拿出来就不要啦, 这个向ArrayList里面添加完点之后还有再扫瞄添加neighbors那, 所以要保留
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if(node == null) {
            return null;
        }
        
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        List<UndirectedGraphNode> nodes = new ArrayList<UndirectedGraphNode>();
       
        //clone nodes克隆所有的点
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label)); //先加入第一个结点
        
        int start = 0;
        while(start < nodes.size()) { //模拟队列的写法, start就是那个指针, 不断向后走, 就相当于poll啦, 然后元素还没有被删除
            UndirectedGraphNode head = nodes.get(start++); //取出点, 因为start每次都++, 放问过的点就不会再访问到啦
            for(int i = 0; i < head.neighbors.size(); i++) { //添加该点的neighbors
                UndirectedGraphNode neighbor = head.neighbors.get(i);
                if(!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    nodes.add(neighbor); //把没有添加过的结点添加进去, 这就引起了nodes的size的变化
                }
            }
        }
        
        //clone neighbors克隆所有的边
        for(int i = 0; i < nodes.size(); i++) {
            UndirectedGraphNode newNode = nodes.get(i); //取出nodes中的点
            for(int j = 0; j < newNode.neighbors.size(); j++) { //按照该点neighbors的数量遍历
                map.get(newNode).neighbors.add(map.get(newNode.neighbors.get(j))); //该点neighbor中的点加入对应的其它点的neighbor
            }
        }
        
        return map.get(node);//在新的结点中添加完啦它的neighbors, 返回这个结点后, 就可以根据neighbors找其它点啦; 原图修改后也不会影响到新的图, 
        //已然分离啦
    }
}
