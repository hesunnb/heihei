/*Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

Example
Given binary tree {3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7
 

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]*/

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
     * @return: A list of lists of integer include 
     *          the zigzag level order traversal of its nodes' values 
     */
     
    //九章给的用栈来实现的，九章的答案思路更清晰，所以是首选
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)
        {
            return result;
        }
        Stack<TreeNode> curlevel = new Stack<TreeNode>();
        Stack<TreeNode> nextlevel = new Stack<TreeNode>();
        Stack<TreeNode> tmp;
    
        curlevel.push(root);
        boolean normalorder = true;
        while(!curlevel.isEmpty())
        {
            ArrayList<Integer> curlevelresult = new ArrayList<Integer>();
            while(!curlevel.isEmpty())
            {
                TreeNode tn = curlevel.pop();
                curlevelresult.add(tn.val);
                if(normalorder) //这里跟LinkedList那个处理方法类似
                {
                    if(tn.left != null)
                    {
                        nextlevel.push(tn.left);
                    }
                    if(tn.right != null)
                    {
                        nextlevel.push(tn.right);
                    }
                }
                else
                {
                    if(tn.right != null)
                    {
                        nextlevel.push(tn.right);
                    }
                    if(tn.left != null)
                    {
                        nextlevel.push(tn.left);
                    }
                }
            }
            tmp = curlevel; //交换一下
            curlevel = nextlevel;
            nextlevel = tmp;
            result.add(curlevelresult);
            normalorder = !normalorder; //正序与非正序
        }
        return result;
    }
      
    /*public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
  
        //自己写的用两个LinkedList来实现的
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            if(root == null)
            {
                return result;
            }
            
            LinkedList<TreeNode> Q1 = new LinkedList<TreeNode>();
            LinkedList<TreeNode> Q2 = new LinkedList<TreeNode>();
            Q1.add(root);
            
            int flag = 0;
            while(!Q1.isEmpty() || !Q2.isEmpty()) //有点就继续，没有点就退出了，说明刚刚加进来的点没有子节点了
            {
                ArrayList<Integer> level = new ArrayList<Integer>();
                 //为的就是保留上次size的值，不能让size随着节点数的增加而实时变化
                if(flag == 0) //奇数层
                {
                    int size = Q1.size(); //两个Q1和Q2的大小得分开计算并使用
                    for(int i = 0; i < size; i++) //每层节点的遍历
                    {
                        TreeNode tn = Q1.get(0); //得到第一个值
                        level.add(tn.val);
                        
                        if(tn.left != null)
                        {
                            Q2.add(0, tn.left); //Q1,Q2交替放节点
                        }
                        
                        if(tn.right != null)
                        {
                            Q2.add(0, tn.right);
                        }
                        Q1.remove(0); //删掉第一个值
                    }
                    result.add(level); //每层单独加一次
                    flag = 1;
                    continue;
                }
                
                if(flag == 1) //偶数层
                {
                    int size = Q2.size();
                    for(int i = 0; i < size; i++)
                    {
                        TreeNode tn = Q2.get(0);
                        level.add(tn.val);
  
                        if(tn.right != null)
                        {
                            Q1.add(0, tn.right);
                        }
                        
                        if(tn.left != null)
                        {
                            Q1.add(0, tn.left);
                        }
                        Q2.remove(0);
                    }
                    result.add(level);
                    flag = 0;
                }
            }
            return result;
    }*/
}
