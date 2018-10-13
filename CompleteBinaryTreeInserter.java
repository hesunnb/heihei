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

//solution1:
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


//solution2: 更优bfs解法, 只遍历一遍树
class CBTInserter {

    /*Store tree nodes to a list self.tree in bfs order.
    Node tree[i] has left child tree[2 * i + 1] and tree[2 * i + 2]

    So when insert the Nth node (0-indexed), we push it into the list.
    we can find its parent tree[(N - 1) / 2] directly.*/
    List<TreeNode> tree;
    public CBTInserter(TreeNode root) {
        tree = new ArrayList<>();
        tree.add(root);
        for (int i = 0; i < tree.size(); i++) { //好处就是只在初始化的时候遍历一遍树, 之后就不需要再遍历树了; 正常bfs做法是
            //每insert一次就要bfs一遍树
            if (tree.get(i).left != null) {
                tree.add(tree.get(i).left);
            }
            if (tree.get(i).right != null) { //正好是完全二叉树, 只需要按序加就可以了
                tree.add(tree.get(i).right);
            }
        }
    }

    public int insert(int v) {
        int N = tree.size(); //先保留大小, 再加入节点
        TreeNode node = new TreeNode(v);
        tree.add(node); //新加的节点也要加在尾部
        if (N % 2 == 1) {
            tree.get((N - 1) / 2).left = node;
        }
        else {
            tree.get((N - 1) / 2).right = node;
        }
        return tree.get((N - 1) / 2).val;
    }

    public TreeNode get_root() {
        return tree.get(0);
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
