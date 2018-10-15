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
     
    
    //用stack过滤法, 此方法也可以用于根据前序序列化字符串重构树
    public boolean isValidSerialization(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if(preorder == null || preorder.length() == 0) {
            return false;
        }
        
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }
    /*below is my understanding

    when you iterate through the preorder traversal string, for each char:

    case 1: you see a number c, means you begin to expand a new tree rooted with c, you push it to stack

    case 2.1: you see a #, while top of stack is a number, you know this # is a left null child, put it there as a mark for next coming 
    node k to know it is being the right child.

    case 2.2: you see a #, while top of stack is #, you know you meet this # as right null child, you now cancel the sub tree (rooted as 
    t, for example) with these two-# children. But wait, after the cancellation, you continue to check top of stack is whether # or a 
    number:

    ---- if a number, say u, you know you just cancelled a node t which is left child of u. You need to leave a # mark to the top of stack.
    So that the next node know it is a right child.

    ---- if a #, you know you just cancelled a tree whose root, t, is the right child of u. So you continue to cancel sub tree of u, and 
    the process goes on and on.*/
}
