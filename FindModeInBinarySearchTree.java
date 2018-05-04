/*Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not 
count).*/

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
    
    //solution1: 开空间就用哈希表, 这个没啥的, 不开空间用全局变量
    Integer prev = null; //用全局变量也没什么不好的
    int count = 1;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) res[i] = list.get(i);
        return res;
    }
    
    private void traverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        traverse(root.left, list);
        if (prev != null) {
            if (root.val == prev)
                count++;
            else
                count = 1;
        }
        if (count > max) { //如果只有一个根节点, 那么这里也会直接把根节点加进来, 不用要求这个二叉搜索树至少有两个节点
            max = count; 
            list.clear(); //每访问一个节点, 都会比较count和max进而操作这个list, 对于每一个节点都操作的好处就是比如一个树中有值为2,1,3的节点,
            list.add(root.val); //数量分别是1,3,4, 所以当访问完3的最后一个节点之后, 直接就放入了list, 所以不会落节点
        } else if (count == max) {
            list.add(root.val);
        }
        prev = root.val;
        traverse(root.right, list);
    }
   
   
    //solution2: two pass, 第一次找出mode的个数, 然后第二次把mode都放进数组; 它这个在discuss里面叫properO(1)space, 因为用
    //ArrayList的时候, 如果一棵树全都是unique的节点, 那么Arraylist就是O(n)space, 因为最后还要把ArrayList拷贝给数组, 所以在这里
    //把ArrayList算到了用空间的范畴, 这个就看具体怎么看了, 面试的时候solution1为主, 这个讨论的时候用
    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    
    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null) //第一次modes为空, 统计modeCount个数; 第二次modes不为空, 向modes中放值
                modes[modeCount] = currVal;
            modeCount++;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }
}
