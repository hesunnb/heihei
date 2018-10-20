/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
     
    /*一个有环的有向图不能被拓扑排序；所以拓扑排序也可以用来判断一个有向图有没有环(就看result中最后点的数目是不是和graph最开始
    给的点的数目相同，少了就是有环)*/
    
    //这里的label值可以重复，但是这道题跟lebal没啥太大关系
    
    //思路：找入度为0，首先能拓扑排序的图肯定有一个入度为0的点，接着找它的neighbors，然后每次减去一，直到为0，依次找所有点即可
    //复杂度是O(m),m是图中边的个数(每个结点访问neighbors的时候都会走相应的边，所以当把所有边走一遍的时候就遍历完啦)
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        if(graph == null) {
            return null;
        }
        //添加所有点到哈希表(除啦入度的点)
        HashMap<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        
        for(int i = 0; i < graph.size(); i++) {
            DirectedGraphNode node = graph.get(i);
            for(int j = 0; j < node.neighbors.size(); j++) {
                DirectedGraphNode neighbor = node.neighbors.get(j);
                if(map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                }
                else {
                    map.put(neighbor, 1);
                }
            }
        }
        
        //找到那个没有入度的点,没有入度的点可以有很多个，它们也属于同一级，也是第一批装进result和队列的
        Queue<DirectedGraphNode> nodes = new LinkedList<DirectedGraphNode>();
        for(int i = 0; i < graph.size(); i++) {
            DirectedGraphNode node = graph.get(i);
            if(!map.containsKey(node)) {
                nodes.offer(node);
                result.add(node);
            }
        }
        
        //从没有入度的点开始，依次向下添加neighbors的点，进行排序
        while(!nodes.isEmpty()) {
            DirectedGraphNode node = nodes.poll();
            for(int i = 0; i < node.neighbors.size(); i++) {
                DirectedGraphNode neighbor = node.neighbors.get(i);
                map.put(neighbor, map.get(neighbor) - 1);
                if(map.get(neighbor) == 0) {
                    nodes.offer(neighbor);
                    result.add(neighbor);
                }
            }
        }
        return result;
    }
}
