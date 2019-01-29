/*Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or 
memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization 
algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized 
to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this 
format, so please be creative and come up with different approaches yourself.

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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        List<TreeNode> list = new ArrayList<>(); //这里用的list, 目的是把整棵树用bfs装全, 包括null
        list.add(root); //这个先加一个的原因是为了能够循环加入节点
        for(int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if(node != null) {
                list.add(node.left);
                list.add(node.right);
            }
        }
        
        //把queue尾部的那些多余的null都去掉, 所以正常的表达式一定以结点结尾, null没有必要加, 连接null是多余的操作
        while(list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0).val); //根肯定不是空, 所以先加进去
        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append("," + list.get(i).val); //先放一个值的原因是这里都是","+值, 这样不会让第一个值是","
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) {
            return null;
        }
        
        List<TreeNode> list = new ArrayList<>(); //和index一起用于组建树
        String[] dataArray = data.split(","); //用于一个点一个点扫描
        boolean isLeftChild = true;
        int index = 0;
        
        list.add(new TreeNode(Integer.parseInt(dataArray[0]))); //先加一个的原因是下面的循环要构建树, 
        //所以根节点一定先要在queue里面
        for(int i = 1; i < dataArray.length; i++) {
            if(!dataArray[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(dataArray[i]));
                if(isLeftChild) {
                    list.get(index).left = node;
                } else {
                    list.get(index).right = node;
                }
                list.add(node);
            }
            
            if(isLeftChild == false) {
                index++; //访问完右子树就是访问完一个结点啦, 这时候index++, 访问下一个结点
            }
            
            isLeftChild = !isLeftChild; //一定要先判断isLeftChild, 然后再取反, 否则顺序就乱啦
        }
        return list.get(0);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
