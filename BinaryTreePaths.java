/* Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5

All root-to-leaf paths are:

["1->2->5", "1->3"]*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
   
   //version1: 字符串递归
    public List<String> binaryTreePaths(TreeNode root) {
        
        List<String> result = new ArrayList<String>();
        if(root != null) {
            treePathHelper(root, result, "");
        }
        return result; //null直接返回result
    }
    
    //在这里用字符串不会出问题的原因是在递归的每一个栈里面都有一个path引用, 而每次传给path的字符串都不一样, 所以都会在堆区开辟
    //新的字符串, 所以每个栈里面path指向的都是不同的字符串
    private void treePathHelper(TreeNode root, List<String> result, String path) {
        if(root.left == null && root.right == null) { //左右都null就是叶子节点, 就用传过来那个path(都是1->2->这样以->结尾的), 
        //直接加上叶子节点的值
            result.add(path + root.val);
        }
        if(root.left != null) { //"" + 1 + "->"这样加, 比非递归整体靠前一位
            treePathHelper(root.left, result, path + root.val + "->"); //用之前的path加上自己的值再加上->
        }
        if(root.right != null) {
            treePathHelper(root.right, result, path + root.val + "->"); //同理
        }
    }
    
    
    //version2: 非递归
    public List<String> binaryTreePaths(TreeNode root) {
        
        List<String> result = new ArrayList<String>();
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        Queue<String> path = new LinkedList<String>();
        
        if(root == null) {
            return result;
        }
        
        nodes.offer(root);
        path.offer(String.valueOf(root.val));
        while(!nodes.isEmpty()) { //这个是从"1" + "->" + "2"这样加, 相对字符串递归的方式向后整体窜了一位
            TreeNode node = nodes.poll();
            if(node.left == null && node.right == null) {
                result.add(path.poll());
            } else { //这里一定要加else, 不加的话如果是叶子节点满足上面的条件就会poll一个, 不加else还会接着poll, poll出的值就多了
                String str = path.poll();
                if(node.left != null) {
                    nodes.offer(node.left);
                    path.offer(str + "->" + node.left.val);
                } 
                if(node.right != null) {
                    nodes.offer(node.right);
                    path.offer(str + "->" + node.right.val);
                }
            }
        }
        return result;
    }
    
    
    //version3: StringBuilder递归
    public List<String> binaryTreePaths(TreeNode root) {
        // Write your code here
        
        List<String> result = new ArrayList<String>();
        if(root == null) {
            return result;
        }
        
        StringBuilder sb = new StringBuilder();
        pathHelper(root, result, sb);
        return result;
    }
    
    private int pathHelper(TreeNode root, List<String> result, StringBuilder sb) {
        if(root == null) {
            return -1;
        }
        StringBuilder temp = new StringBuilder(sb); //这里必须每次都要用temp新建, 因为如果不新建, 每个栈里的sb都会指向
        //同一个new的实例(和字符串不一样)
        temp.append(root.val + "->");
        
        int left = pathHelper(root.left, result, temp);
        int right = pathHelper(root.right, result, temp);
        
        if(left == -1 && right == -1) {
            result.add(temp.substring(0, temp.length() - 2));
        }
        return 0;
    }
}
