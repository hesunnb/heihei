/*A quadtree is a tree data in which each internal node has exactly four children: topLeft, topRight, bottomLeft and bottomRight. Quad 
trees are often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.

We want to store True/False information in our quad tree. The quad tree is used to represent a N * N boolean grid. For each node, it will 
be subdivided into four children nodes until the values in the region it represents are all the same. Each node has another two boolean 
attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value 
of the region it represents.

For example, below are two quad trees A and B:

A:
+-------+-------+   T: true
|       |       |   F: false
|   T   |   T   |
|       |       |
+-------+-------+
|       |       |
|   F   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight: T
bottomLeft: F
bottomRight: F

B:               
+-------+---+---+
|       | F | F |
|   T   +---+---+
|       | T | T |
+-------+---+---+
|       |       |
|   T   |   F   |
|       |       |
+-------+-------+
topLeft: T
topRight:
     topLeft: F
     topRight: F
     bottomLeft: T
     bottomRight: T
bottomLeft: T
bottomRight: F

 

Your task is to implement a function that will take two quadtrees and return a quadtree that represents the logical OR (or union) of the 
two trees.

A:                 B:                 C (A or B):
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       | F | F |  |       |       |
|   T   |   T   |  |   T   +---+---+  |   T   |   T   |
|       |       |  |       | T | T |  |       |       |
+-------+-------+  +-------+---+---+  +-------+-------+
|       |       |  |       |       |  |       |       |
|   F   |   F   |  |   T   |   F   |  |   T   |   F   |
|       |       |  |       |       |  |       |       |
+-------+-------+  +-------+-------+  +-------+-------+

Note:

    Both A and B represent grids of size N * N.
    N is guaranteed to be a power of 2.
    If you want to know more about the quad tree, you can refer to its wiki.
    The logic OR operation is defined as this: "A or B" is true if A is true, or if B is true, or if both A and B are true.*/
    
    
/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        
        if(quadTree1 == null || quadTree2 == null) {
            return null;
        }
        
        return intersectHelper(quadTree1, quadTree2);
    }
    
    private Node intersectHelper(Node quadTree1, Node quadTree2) {
        
        if(quadTree1 == null || quadTree2 == null) { //如果给的是一个不完整的四叉树, 比如四叉树的某一个叉是null, 这个就用到了
            return null;
        }
        
        if(quadTree1.isLeaf) { //这里有一个保留树的过程
            if(quadTree1.val) {
                return quadTree1; //如果既是leaf又是true, 就保留quadTree1
            } else {
                return quadTree2; //如果此时val是false, 那么就要保留quadTree2, 无论保留谁, 这个分支递归到这里也就结束了
                //就是保留了一个结果, 而不是替换, 把quadTree2整体替换为一个false, 这是不对的
            }
        }
        
        if(quadTree2.isLeaf) { //同理
            if(quadTree2.val) {
                return quadTree2;
            } else {
                return quadTree1;
            }
        }
        
        Node node = new Node();
        node.topLeft = intersectHelper(quadTree1.topLeft, quadTree2.topLeft);
        node.topRight = intersectHelper(quadTree1.topRight, quadTree2.topRight);
        node.bottomLeft = intersectHelper(quadTree1.bottomLeft, quadTree2.bottomLeft);
        node.bottomRight = intersectHelper(quadTree1.bottomRight, quadTree2.bottomRight);
        
        //所以这个null判断还是有作用的, 如果null or 一个节点的结果是null, 那么这个null的判断就有作用, 就看怎么定义了
        if (node.topLeft != null && node.topRight != null && node.bottomLeft != null && node.bottomRight != null &&
            node.topLeft.isLeaf && node.topRight.isLeaf && node.bottomLeft.isLeaf && node.bottomRight.isLeaf &&
            node.topLeft.val == node.topRight.val && 
            node.topRight.val == node.bottomRight.val &&
            node.bottomRight.val == node.bottomLeft.val) { //如果四个节点都是leaf然后都是true, 那么说明下面的结果是统一的,
            //然后就修改当前节点为leaf, 然后val随其中一个子节点来就行; 同理, 如果四个节点都是leaf然后都是false, 也一样要抛弃下面的所有子树
            node.isLeaf = true;
            node.val = node.topLeft.val;
        }
        return node;
    }
}
