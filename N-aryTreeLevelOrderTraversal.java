/*Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:
                      1
                    / | \
                   3  2  4
                  / \
                 5   6

We should return its level order traversal:
[
     [1],
     [3,2,4],
     [5,6]
]

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
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        Queue<Node> Q = new LinkedList<Node>();
        Q.offer(root);

        while(!Q.isEmpty()) { //有点就继续，没有点就退出了，说明刚刚加进来的点没有子节点了
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = Q.size(); //为的就是保留上次size的值，不能让size随着节点数的增加而实时变化
            for(int i = 0; i < size; i++) {
                Node node = Q.poll();
                level.add(node.val);

                for(int j = 0; j < node.children.size(); j++) {
                    Q.offer(node.children.get(j));
                }
            }
            result.add(level);
        }
        return result;
    }
}
