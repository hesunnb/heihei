/*We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents 
the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the 
same.

Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute 
for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding quad tree:



It can be divided according to the definition above:



 

The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.

For the non-leaf nodes, val can be arbitrary, so it is represented as *.



Note:

N is less than 1000 and guaranteened to be a power of 2.
If you want to know more about the quad tree, you can refer to its wiki.*/

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

    //这道题四叉树的给例是图片, 无法粘贴过来, 所以就这样了, 具体题目回leetcode看
    public Node construct(int[][] grid) {
        
        if(grid == null || grid[0].length == 0) {
            return null;
        }
        
        return constructHelper(grid, 0, grid.length-1, 0, grid[0].length-1);
    }
    
    private Node constructHelper(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        
        Node node = new Node();
        node.isLeaf = false;
        node.val = false;
        
        boolean same = true; //same初始定为true的好处就是下面same==false容易判断
        for(int i = rowStart; i <= rowEnd; i++) {
            for(int j = colStart; j <= colEnd; j++) {
                if(grid[i][j] != grid[rowStart][colStart]) {
                    same = false;
                }
            }
        }
 
        if(same) { //然后same为真这里一定会执行到, 递归的终止条件就是最后rowStart==rowEnd了, colStart==colEnd了, 按照道理无论怎样same
            //最后都会为真, 就算上面的same==false最后没有检查到, same依旧初始化为真, 所以还是会返回node
            node.isLeaf = true;
            node.val = grid[rowStart][colStart] == 1 ? true : false;
            return node;
        } else {
            int rowMid = rowStart + (rowEnd-rowStart) / 2;
            int colMid = colStart + (colEnd-colStart) / 2;
            node.topLeft = constructHelper(grid, rowStart, rowMid, colStart, colMid);
            node.topRight = constructHelper(grid, rowStart, rowMid, colMid+1, colEnd);
            node.bottomLeft = constructHelper(grid, rowMid+1, rowEnd, colStart, colMid);
            node.bottomRight = constructHelper(grid, rowMid+1, rowEnd, colMid+1, colEnd);
        }
        return node;
    }
}
