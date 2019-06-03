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
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
