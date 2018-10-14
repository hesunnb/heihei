/*One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is 
a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #

For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an 
algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true

Example 2:

Input: "1,#"
Output: false

Example 3:

Input: "9,#,#,1"
Output: false*/

class Solution {
    /*Some used stack. Some used the depth of a stack. Here I use a different perspective. In a binary tree, if we consider null as 
    leaves, then

    all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
    all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

    Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree -
    indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we
    increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will 
    be zero when finished.*/
    //solution1: 入度出度法
    public boolean isValidSerialization(String preorder) {
        
        if(preorder == null || preorder.length() == 0) {
            return false;
        }
        
        String[] nodes = preorder.split(",");
        int diff = 1; //这个初始的1是给root节点留的
        for (String node: nodes) { //入度出度法, 非常好
            if (--diff < 0) { //首先遇到root节点的时候, 把diff减为0, 这样就等同于从diff==0开始了
                return false;
            }
            if (!node.equals("#")) { //加出度
                diff += 2;
            }
        }
        return diff == 0; //如果是前序序列化, 入度出度最终相等
    }
}
