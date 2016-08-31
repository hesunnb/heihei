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
 
 
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: buttom-up level order a list of lists of integer
     */
     
    //BFS正常遍历
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
         ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            if(root == null)
            {
                return result;
            }
            
            Queue<TreeNode> Q = new LinkedList<TreeNode>();
            Q.offer(root);
            
            while(!Q.isEmpty()) //有点就继续，没有点就退出了，说明刚刚加进来的点没有子节点了
            {
                ArrayList<Integer> level = new ArrayList<Integer>();
                int size = Q.size(); //为的就是保留上次size的值，不能让size随着节点数的增加而实时变化
                for(int i = 0; i < size; i++) //每层节点的遍历
                {
                    TreeNode tn = Q.poll();
                    level.add(tn.val);
                    
                    if(tn.left != null)
                    {
                        Q.offer(tn.left);
                    }
                    if(tn.right != null)
                    {
                        Q.offer(tn.right);
                    }
                }
                result.add(0, level); //就和１那个问题差了这句话，这个就是每次在result的前面加level，然后就是倒序了
            }
            return result;
    }
    
    //DFS遍历
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) 
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        levelmaker(result, root, 0); //第一行是0level, 依次类推
        return result;
    }   
    
    private void levelmaker(ArrayList<ArrayList<Integer>> result, TreeNode root, int level)
    {
        if(root == null)
        {
            return;
        }
        if(level >= result.size()) //实际上只是每次等于起作用, 保险起见用成>=
        {
            result.add(0, new ArrayList<Integer>()); //leve>=list.size()的时候就是第一次进入这个层的时候, 每次第一回到一个层都要创建一个盒
        }
        levelmaker(result, root.left, level + 1); //对左边levelmaker
        levelmaker(result, root.right, level + 1); //对右边levelmaker
        result.get(result.size() - level - 1).add(root.val); //左右都建完啦, 把自己的值加到相应的盒里
    }
}
