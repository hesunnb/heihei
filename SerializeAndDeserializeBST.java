/*Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or 
memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your 
serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a 
string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    /*One of the ways a BST tree is different from a general binary tree is its structure is wholly dependent on the order in which the
    values are inserted. A string created from a preorder traversal of a BST will tell you the order in which the values were inserted 
    into the tree. Since you just need the order the values were inserted, you do not need to account for null nodes in the string 
    with "#" or "null". Hence, the final string contains only the values and separators, which makes it the most compact possible.*/
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    //前序遍历装树
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }
        sb.append(root.val + ",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }
        String[] dataArray = data.split(","); //serialize完末尾会有一个",", 但是split对于末尾的分隔符忽略, 对于前面的分隔符会产生"".
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < dataArray.length; i++) {
            queue.offer(Integer.parseInt(dataArray[i]));
        }
        return deserializeHelper(queue);
    }
    
    //分隔处变为左边树一个队列, 右边树一个队列
    private TreeNode deserializeHelper(Queue<Integer> queue) {
        if(queue.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(queue.poll());
        Queue<Integer> leftQueue = new LinkedList<>();
        while(!queue.isEmpty() && queue.peek() < root.val) { //比root小的为左树, 装到leftQueue里
            leftQueue.offer(queue.poll());
        }
        root.left = deserializeHelper(leftQueue); //左树队列
        root.right = deserializeHelper(queue); //左树都弹出来, queue剩下的就是右树队列
        return root;
    }
    /*Hi all, I think my solution is pretty straightforward and easy to understand, not that efficient though. And the serialized 
    tree is compact.
    Pre order traversal of BST will output root node first, then left children, then right.

    root left1 left2 leftX right1 rightX
    If we look at the value of the pre-order traversal we get this:

    rootValue (<rootValue) (<rootValue) (<rootValue) |separate line| (>rootValue) (>rootValue)
    Because of BST's property: before the |separate line| all the node values are less than root value, all the node values 
    after |separate line| are greater than root value. We will utilize this to build left and right tree.

    Pre-order traversal is BST's serialized string. I am doing it iteratively.
    To deserialized it, use a queue to recursively get root node, left subtree and right subtree.

    I think time complexity is O(NlogN).
    Errr, my bad, as @ray050899 put below, worst case complexity should be O(N^2), when the tree is really unbalanced.*/
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
