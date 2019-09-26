/*You have two every large binary trees: T1, with millions of nodes, and T2, with hundreds of nodes. 
Create an algorithm to decide if T2 is a subtree of T1. 
Example

T2 is a subtree of T1 in the following case:

       1                3
      / \              / 
T1 = 2   3      T2 =  4
        /
       4

T2 isn't a subtree of T1 in the following case:

       1               3
      / \               \
T1 = 2   3       T2 =    4
        /
       4
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
     
    //时间: O(n + km), where k is the number of occurrences of T2's root in T1.
    //空间: O(log(n) + log(m)), 原因是: n和m分别是T1和T2树的节点数, log(n)和log(m)其实就是T1和T2树的高度, 即递归开栈最多只能开到树的深度的栈
    public boolean isSubtree(TreeNode T1, TreeNode T2) {
        // write your code here
        if (T2 == null) { //null是任何树的子树
            return true;
        }
        if (T1 == null) {
            return false;
        }
        
        if (isIdentical(T1, T2)) {
            return true;
        }
        if (isSubtree(T1.left, T2) || isSubtree(T1.right, T2)) { //左子树与右子树有一个和T2一样的, 就返回真; 短路或, 
        //有一个为真递归就终止了
            return true;
        }
        return false;
    }
    
    private boolean isIdentical(TreeNode a, TreeNode b) {
        // Write your code here
        
        if (a == null || b == null) {
            return a == b;
        }
        if (a.val != b.val) { //起到首节点判断的作用, 首节点不相等就不向下递归了
            return false;
        }
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
       
    
    //solution2:
    //t2是t1子树, 那么分别前序遍历两个树得到字符串, t2的字符串应该是t1的一个子串, 源于CrackBook
    //O(n + m) time and O(n + m) space, where n and mare the number of nodes in T1 and T2, respectively.
    public boolean isSubtree(TreeNode tl, TreeNode t2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(tl, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    public void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X"); // Add null indicator, 一定要在字符串中加入null来确定树的形状
            return;
        }

        sb.append("'" + node.val + "'" + " "); // Add root
        /*这里要加个标识符, 一个testCase是比如第一个树是12一个节点, 第二个树是2一个节点, 
        得出的string是: 12 XX, 2 XX, 返回true, 实际上应该是false*/
        getOrderString(node.left, sb); // Add left
        getOrderString(node.right, sb); // Add right
    }
}
