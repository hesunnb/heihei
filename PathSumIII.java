/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    //受subtree的启发, 从每一个节点出发向下走判断有没有和等于sum的某一段路径(注意不用到叶子节点)
    public int pathSum(TreeNode root, int sum) {
        
        if(root == null) {
            return 0;
        }
        int count = pathSumHelper(root, sum, 0, 0); //根左右的方式, 每一个节点计算自己的count
        count += pathSum(root.left, sum); //然后加入左右子树的count
        count += pathSum(root.right, sum);
        return count;
    }
    
    public int pathSumHelper(TreeNode root, int sum, int tempSum, int count) {
        
        if(tempSum + root.val == sum) { //这里就把之前pathSum, pathSum2的条件给取消了, 因为不需要是叶子节点
            count++;
        }
        
        if(root.left != null) {
            count = pathSumHelper(root.left, sum, tempSum + root.val, count); //这里的count不用+=, 因为pathSumHelper里面传入的
            //count就已经是++过的了, 如果count在后面被++过, 这里就直接覆盖掉, 如果在后面没有被++过, 这里返回的还是最开始传入的count
        }
        
        if(root.right != null) {
            count = pathSumHelper(root.right, sum, tempSum + root.val, count);
        }
        return count;
    }
}
