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
     * @return: Level order a list of lists of integer
     */

    //层序遍历有三种实现方法:①一个队列实现②一个队列加一个虚拟节点实现③两个队列实现
   
        // write your code here

        //①一个队列实现(首选), BFS遍历
        public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root)
        {
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
                for(int i = 0; i < size; i++)
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
                result.add(level);
            }
            return result;
        }


        
        //version 2: DFS
        public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root)
        {
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            levelmaker(result, root, 0);
            return result;
        }
        
        private void levelmaker(ArrayList<ArrayList<Integer>> result, TreeNode root, int level)
        {
            if(root == null)
            {
                return;
            }
            if(level >= result.size())
            {
                result.add(new ArrayList<Integer>()); //向result里边加盒, 和2问不一样的就是这里是正序加, 2是反序加盒
            }
            levelmaker(result, root.left, level + 1);
            levelmaker(result, root.right, level + 1);
            result.get(level).add(root.val); //level直接代表啦正序下标
        }


        /*//②一个队列加一个虚拟节点实现
        //队列用法: 1.poll()获取并移除此队列的头，如果此队列为空，则返回 null                                               2.offer，add区别：一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝         。这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked                          异常，而只是得到由 offer() 返回的 false                                                                 3.目前来看，java中的queue只能先用LinkedList实现
        
        public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root)
        {
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            if(root == null)
            {
                return result;
            }
            
            Queue<TreeNode> Q = new LinkedList<TreeNode>();
            Q.offer(root);
            Q.offer(null); //dummy node
            
            ArrayList<Integer> level = new ArrayList<Integer>();
            while(!Q.isEmpty())
            {
                TreeNode tn = Q.poll();
                if(tn == null)
                {
                    if(level.size() == 0)
                    {
                        break;
                    }
                    result.add(level);
                    level = new ArrayList<Integer>();
                    Q.offer(null);
                    continue;
                }
                
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
            return result;
        }*/
    
        /*//③两个队列实现方法
        //利用两个装节点的ArrayList来回倒，一个装根节点，一个装根节点的子节点，然后交换继续，就能实现按层遍历
        
        public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
         
        if(root == null)
        {
            return result;
        }

        ArrayList<TreeNode> Q1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> Q2 = new ArrayList<TreeNode>();
      
        Q1.add(root);
        while(Q1.size() != 0)
        {
            ArrayList<Integer> level = new ArrayList<Integer>();
            Q2.clear();
            for(int i = 0; i < Q1.size(); i++)
            {
                TreeNode tn = Q1.get(i);
                level.add(tn.val);
                
                if(tn.left != null)
                {
                    Q2.add(tn.left);
                }
                if(tn.right != null)
                {
                    Q2.add(tn.right);
                }
            }
            
            // swap q1 and q2
            ArrayList<TreeNode> temp = Q1;
            Q1 = Q2;
            Q2 = temp;
            
            // add to result
            result.add(level);
        }
        return result;
    }*/
}
