/*Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example, given a 3-ary tree:
                    1
                  / | \
                 3  2  4
                / \
               5   6
 
We should return its max depth, which is 3.

Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    
    private int depth = 0;
    public int maxDepth(Node root) {
        
        maxDepthhelper(root, 1);
        return depth;
    }
    
    private void maxDepthhelper(Node node, int curdepth) {
        if(node == null) {
            return;
        }
        
        if(curdepth > depth) {
            depth = curdepth;
        }
        
        for(int i = 0; i < node.children.size(); i++) {
            maxDepthhelper(node.children.get(i), curdepth + 1);
        }
    }
}
