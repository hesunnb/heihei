/*Design an algorithm and write code to serialize and deserialize a binary tree. 
Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree 
is 'deserialization'.

There is no limit of how you deserialize or serialize a binary tree, 
you only need to make sure you can serialize a binary tree to a string and deserialize this string to the original structure.

Example
An example of testdata: Binary tree {3,9,20,#,#,15,7}, denote the following structure:

  3
 / \
9  20
  /  \
 15   7
Our data serialization use bfs traversal. This is just for when you got wrong answer and want to debug the input.

You can use other method to do serializaiton and deserialization.*/

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
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if(root == null)
        {
            return null;
        }
        
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root); //这个先加一个的原因是为了能够循环加入节点
        for(int i = 0; i < queue.size(); i++) //这里的queue加所有的结点, 包括空结点
        {
            TreeNode node = queue.get(i);
            if(node == null)
            {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        
        //把queue尾部的那些多余的null都去掉, 所以正常的表达式一定以结点结尾
        while(queue.get(queue.size() - 1) == null)
        {
            queue.remove(queue.size() - 1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(queue.get(0).val); //根肯定不是空, 所以先加进去
        for(int i = 1; i < queue.size(); i++)
        {
            if(queue.get(i) == null)
            {
                sb.append(",#");
            }
            else
            {
                sb.append(","); //先放一个值的原因是这里都是","+值, 这样不会让第一个值是","
                sb.append(queue.get(i).val);
            }
        }
        
        return sb.toString();
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if(data == null)
        {
            return null;
        }
        
        String storage[] = data.split(",");
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        boolean isLeftChild = true;
        int index = 0; //负责queue的下标
        
        queue.add(new TreeNode(Integer.parseInt(storage[0]))); //先加一个的原因是下面的循环要构建树, 
        //所以根节点一定先要在queue里面
        for(int i = 1; i < storage.length; i++)
        {
            if(!storage[i].equals("#")) //用.equals()!, 不要用!=
            {
                TreeNode node = new TreeNode(Integer.parseInt(storage[i]));
                if(isLeftChild) //如果现在是左子树
                {
                    queue.get(index).left = node;
                }
                else
                {
                    queue.get(index).right = node;
                }
                queue.add(node);
            }
            
            if(!isLeftChild)
            {
                index++; //访问完右子树就是访问完一个结点啦, 这时候index++, 访问下一个结点
            }
            
            isLeftChild = !isLeftChild; //一定要先判断isLeftChild, 然后再取反, 否则顺序就乱啦
        }
        
        return queue.get(0);
    }
}


