/*A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far 
left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

    CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
    CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns 
    the value of the parent of the inserted TreeNode;
    CBTInserter.get_root() will return the head node of the tree.


Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]

Example 2:

Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]

 

Note:

    The initial given tree is complete and contains between 1 and 1000 nodes.
    CBTInserter.insert is called at most 10000 times per test case.
    Every value of a given or inserted node is between 0 and 5000.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class CBTInserter {

    //BFS解法
    TreeNode root;
    public CBTInserter(TreeNode root) {
        this.root = root;
    }
    
    public int insert(int v) {
        if(root == null) { //按照道理root不能是null
            root = new TreeNode(v); //如果非是null, 那么就只好让root为插入的节点, 返回root的值了
            return root.val;
        }

        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.offer(root);

        while(!Q.isEmpty()) { //有点就继续，没有点就退出了，说明刚刚加进来的点没有子节点了
            int size = Q.size(); //为的就是保留上次size的值，不能让size随着节点数的增加而实时变化
            for(int i = 0; i < size; i++) {
                TreeNode tn = Q.poll();

                if(tn.left != null) {
                    Q.offer(tn.left);
                } else {
                    tn.left = new TreeNode(v); //左边是null那么直接连左边
                    return tn.val;
                }
                
                if(tn.right != null) {
                    Q.offer(tn.right);
                } else {
                    tn.right = new TreeNode(v); //左边不null, 右边null就直接连右边
                    return tn.val;
                }
            }
        }
        
        return root.val;
    }
    
    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
