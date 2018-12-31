/*Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child 
connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

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
     
    //题目的意思是找整个树里最大的路径和, 可以是任意路径, 任意起点, 任意终点, 只要路径和最大就行
    //思路就是把anynode to anynode拆成root to anynode, 先求single path(root to any)
    //any to any有三种情况, 最大完全在左子树, 最大完全在右子树上, 最大跨过根节点左右加根
    public int maxPathSum(TreeNode root) {
        // write your code here
        
        //思路:（分治法）
        //复杂度:O(n), helper内的计算都是O(1), 共有n个点, 所以O(n)
        
        // singlePath: 从root往下走到任意点的最大路径, 这条路径可以不包含任何点
        //(比如根节点为-100, 这时候计算singlepath的时候, 把-100加上会小于０, 就把整个树的single path值全都舍弃了
        //), 其实这是为了每个子树上面的节点服务的, 比如每个节点的左子树, 
        //这个左子树也有一个根节点，这个左子树因为最终single path计算结果为负, 会把整个左子树舍弃(也就是赋值为0), 
        //然后也就不会参与到计算max的过程了
        
        // maxPath: 从树中任意到任意点的最大路径, 这条路径至少包含一个点(这个点最开始就是每个叶子节点, 最后会和到一个值,
        //就是max, 所以要是整个树只有根节点，它也会留下一个值, 这个值的保证正好是由Integer.MIN_VALUE来保证的, 
        //因为它比谁都小, 什么负数最开始都能在max的地方留下, 最后max就成了保留每步最大值的变量)
        
        ReturnType result = maxpathhelper(root);
        return result.maxpath;
    }
    
    class ReturnType {
        int singlepath;
        int maxpath;
        ReturnType(int singlepath, int maxpath) {
            this.singlepath = singlepath;
            this.maxpath = maxpath;
        }
    }
    
    private ReturnType maxpathhelper(TreeNode root) {
        if(root == null) {
            return new ReturnType(0, Integer.MIN_VALUE); //返回给上一个节点，这个Integer.MIN_VALUE会保证什么负数最开始都能在max的地方留下
        }
        
        //Divide: 分别找左子树和右子树
        ReturnType left = maxpathhelper(root.left); 
        ReturnType right = maxpathhelper(root.right);
        
        //Conquer:
        int singlepath = Math.max(left.singlepath, right.singlepath) + root.val; //这个就是计算root to 叶子节点的值，要把根节点自己的值加上
        singlepath = Math.max(0, singlepath); //这个就可以达到to anynode,因为它把结果为负数的子树给舍掉了，就是赋值为0,只要结果为正的子树的值,
        //这样就不会包含任何点，要是作为单独一道题，就是求singlepath最大，那就合一下：
        //Math.max(0, Math.max(left.singlepath, right.singlepath)) + root.val;这样根节点的值就留下来了
        
        int maxpath = Math.max(left.maxpath, right.maxpath); //这个是选出左右子树最大的max值
        maxpath = Math.max(maxpath, left.singlepath + right.singlepath + root.val);
        //要是计算跨过根节点的max值需要将左子树的root to any + 右子树的root to any + 
        //根节点的值再进行比较，而singlepath值就是在这里用到的，就是为求max而存在的：加上一个根max变大了，那就用这个；没
        //大，就用原来的
        return new ReturnType(singlepath, maxpath); //return他自己的singlepath和maxpath
    }
    
    
    //version 2: 把SinglePath也定义为至少包含一个点, 上面那个是singlepath可以不包含点, 因为计算singlepath的时候带上啦root.val
    private class ResultType {
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        // Conquer, 就这个地方和上面不一样
        int singlePath = Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val; //这里每次计算完root.val的值都会留下, 
        //所以至少包含啦点

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, Math.max(left.singlePath, 0) + Math.max(right.singlePath, 0) + root.val); //对于负数的子树在这里进行舍弃, 
        //就是把负值的singlepath舍掉

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
       
    
    //version3: 最简版本, 不用自己定义类
    int maxValue; //用全局变量存最大路径和
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathHelper(root);
        return maxValue;
    }
    
    private int maxPathHelper(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathHelper(node.left)); //左最大singlePath
        int right = Math.max(0, maxPathHelper(node.right)); //右最大singlePath
        maxValue = Math.max(maxValue, left + right + node.val); //求maxPath
        return Math.max(left, right) + node.val; //算上自己根节点的值返回新计算的singlePath
    }
}
