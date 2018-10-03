/*Given an n-ary tree, return the preorder traversal of its nodes' values.

 
For example, given a 3-ary tree:
                1
              / | \
             3  2  4
            / \
           5   6
 
Return its preorder traversal as: [1,3,5,6,2,4].

 
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
 
    //verion1: 递归
    public List<Integer> preorder(Node root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        travelsal(root, result);
        return result;
    }
    
    public void travelsal(Node root, ArrayList<Integer> result) {
        if(root == null) {
            return;
        }
        
        result.add(root.val);
        for(int i = 0; i < root.children.size(); i++) {
            travelsal(root.children.get(i), result);
        }
    }
 
 
    //version2: 迭代
    public List<Integer> preorder(Node root) {
        Stack<Node> sk = new Stack<Node>();
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) {
            return result;
        }
        
        sk.push(root); //先放根
        while(!sk.isEmpty()) {
            Node node = sk.pop();
            result.add(node.val); 
            for(int i = node.children.size() - 1; i >= 0; i--) {
                sk.push(node.children.get(i));
            }
        }
        return result;
    }
}

