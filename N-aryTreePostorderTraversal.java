/*Given an n-ary tree, return the postorder traversal of its nodes' values.

For example, given a 3-ary tree:
                     1
                  /  |  \
                 3   2   4
                / \   \  / \
               5   6   7 8  9
 
Return its postorder traversal as: [5,6,3,2,4,1].

Note: Recursive solution is trivial, could you do it iteratively?*/

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
    public List<Integer> postorder(Node root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        traversal(root, result);
        return result;
    }
    
    private void traversal(Node root, ArrayList<Integer> result) {
        if(root == null) {
            return;
        }
        
        for(int i = 0; i < root.children.size(); i++) {
            traversal(root.children.get(i), result);
        }
        result.add(root.val);
    }
}

