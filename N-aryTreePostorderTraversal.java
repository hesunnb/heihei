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
  
    //version1: 递归
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
  
    
    //version2: 迭代
    public List<Integer> postorder(Node root) {
        Stack<Node> sk = new Stack<Node>();
        List<Integer> result = new ArrayList<Integer>();
        
        if(root == null) {
            return result;
        }
        
        sk.push(root);
        while(!sk.isEmpty()) {
            Node node = sk.pop();
            result.add(0, node.val); //每次在头加入值, 后序是左右根, 倒着就是根右左, 和前序非常像, 所以用根右左遍历, 
            //然后倒着插入到result当中就可以了！
            for(int i = 0; i < node.children.size(); i++) {
                sk.push(node.children.get(i));
            }
        }
        return result;
    }
}

