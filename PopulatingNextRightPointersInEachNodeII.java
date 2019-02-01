/*Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //这道题用bfs行的原因是因为有next指针的原因, dummyhead.next每次指向树每一行的行首, 然后每一行用next指针进行遍历, 普通的bfs还是得用队列
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummyhead = new TreeLinkNode(0);
        TreeLinkNode current = dummyhead;
        while (root != null) {
            current = dummyhead; //每遍历完一行, current就从dummyhead从新出发
            while (root != null) {
                if (root.left != null) { 
                    current.next = root.left; 
                    current = current.next; 
                }
                if (root.right != null) { 
                    current.next = root.right; 
                    current = current.next; 
                }
                root = root.next;
            }
            root = dummyhead.next; //root下窜到下一行行首
            dummyhead.next = null; //一定要赋值为null, {1,2}这个例子是个证明
        }
    }
    /*一个testcase, 注意9那个地方左右都没有子节点, 所以dfs要注意
                              2
                           /     \
                          1       3
                         / \     / \
                        0   7   9   1
                       /   / \     / \
                      2   1   0    8   8
                             / 
                            7
    */
}
