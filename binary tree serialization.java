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
     
    //serialize: 
    //思路就是把一个树的所有节点(包括空)放到一个ArrayList里面, 并把尾部的多余的null去掉, 然后扫一遍ArrayList把null变为#,
    //同时把值加到StringBuilder里面, 然后把StringBuilder变为String

    public String serialize(TreeNode root) {
        // write your code here
        if(root == null)
        {
            return null;
        }
        
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root);
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
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        
        return sb.toString(); //一定要变, StringBuilder和String不一样
    }
    
    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
     
    //deserialize:
    //思路就是把给过来的字符串按照","拆解, 扫一遍所拆解的数组, 组建queue里结点的树, 同时把有用的结点加到queue里
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
        
        queue.add(new TreeNode(Integer.parseInt(storage[0])));
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
                queue.add(node); //这里的queue只加有用的结点
            }
            
            if(!isLeftChild)
            {
                index++; //访问完右子树就是访问完一个结点啦, 这时候index++, 访问下一个结点
            }
            
            isLeftChild = !isLeftChild; //更改左右子树顺序, 一定要先判断isLeftChild, 然后再取反, 否则顺序就乱啦
        }
        
        return queue.get(0);
    }
}
