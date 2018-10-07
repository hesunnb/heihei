/*Print a binary tree in an m*n 2D string array following these rules:

The row number m should be equal to the height of the given binary tree.
The column number n should always be an odd number.
The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row 
where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the 
left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom 
part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none 
subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't 
need to leave space for both of them.
Each unused space should contain an empty string "".
Print the subtrees following the same rules.

Example 1:
Input:
     1
    /
   2
Output:
[["", "1", ""],
 ["2", "", ""]]
 
Example 2:
Input:
     1
    / \
   2   3
    \
     4
Output:
[["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 
Example 3:
Input:
      1
     / \
    2   5
   / 
  3 
 / 
4 
Output:

[["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
Note: The height of binary tree is in the range of [1, 10].*/

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

    //注意这道题左右两边子树的位置确定: 是按照二分来的, 永远都是两个值(在不同层)的中间, 不是+4,-4,+2,-2,+1,-1那种
    public List<List<String>> printTree(TreeNode root) {

        List<List<String>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        
        int height = maxDepth(root); //先求树的高度
        int length = 0;
        for(int i = 0; i < height; i++) { //根据高度来求每一行的宽度(找规律:1,1; 2,3; 3,7; 4,15...)
            length = length * 2 + 1; //题目中说了列数永远是奇数
        }

        for(int i = 0; i < height; i++) { //先把result填满, 这样dfs的时候就不用新建list了, 否则dfs期间无法建立list
            List<String> list = new ArrayList<>();
            for(int j = 0; j < length; j++) {
                list.add("");
            }
            result.add(list);
        }
        
        printTreeHelper(root, 0, length - 1, 0, result);
        return result;
    }

    private void printTreeHelper(TreeNode root, int start, int end, int level, List<List<String>> result) {
        if(root == null) {
            return;
        }
        
        int mid = start + (end-start) / 2; 
        List<String> list = result.get(level);
        list.set(mid, String.valueOf(root.val));
        printTreeHelper(root.left, start, mid-1, level+1, result); //因为每行都是奇数, 所以+-直接就可以了
        printTreeHelper(root.right, mid+1, end, level+1, result);
    }
    
    private int maxDepth(TreeNode root) {
        
        if(root == null) {
            return 0;
        }
        
        int left = maxDepth(root.left); //先求左子树最大
        int right =  maxDepth(root.right); //再求右子树最大
        return Math.max(left, right) + 1; //把最大值拿回来再加上自己的节点
 
    }
}
