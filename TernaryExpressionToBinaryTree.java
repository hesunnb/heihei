/*举个例⼦子，就是
a?b?c:d:e转化成
a
/ \
b e
/ \
c d

我⽤用递归写的，time complexity O(nlogn)(小于nlogn, 要扫描logn回, 但是每次扫描的长度会小于n), space O(1). 
时间复杂度⾼高是因为要查找String的分割点，这个每次都要O(N)
*/

package pocketgems;

import java.util.*;

public class TernaryExpressionToBinaryTree {

	static class TreeNode { //在这里要写成静态类
		char value;
		TreeNode left;
		TreeNode right;

		TreeNode(char value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "T?2:3";
		String s2 = "F?1:T?4:5";
		String s3 = "T?T?F:5:3";
		String s4 = "a?b?c:d:e";
		
		TreeNode root1 = solve(s1);
		preorderTraversal(root1);
		
		System.out.println();
		
		TreeNode root2 = solve(s2);
		preorderTraversal(root2);
		
		System.out.println();
		
		TreeNode root3 = solve(s3);
		preorderTraversal(root3);
		
		System.out.println();
		
		TreeNode root4 = solve(s4);
		preorderTraversal(root4); //验证OK
	}

	public static TreeNode solve(String s) {

		if (s == null || s.length() == 0) {
			return null;
		}

		if (s.length() == 1) { //只剩一个字母就是叶子节点了
			return new TreeNode(s.charAt(0));
		}

		int flag = 0;
		int mid = 0;

		for (int i = 2; i <= s.length() - 1; i++) { //从第二个位置向后扫描, 空出第一个值和?
			if (s.charAt(i) == '?') { //如果是问号flag就++
				flag++;
			} else if (s.charAt(i) == ':') { //是冒号的时候
				if (flag == 0) { //flag如果是0就是切割点
					mid = i; //mid记录位置
					break;
				} else {
					flag--; //不是切割点flag就--
				}
			}
		}

		TreeNode head = new TreeNode(s.charAt(0)); //字符串的第一个值作为根
		TreeNode temp_left = solve(s.substring(2, mid)); //左边
		TreeNode temp_right = solve(s.substring(mid + 1, s.length())); //右边
		head.left = temp_left;
		head.right = temp_right;
		return head;
	}
	
	
	//以下用于测试
	public static void preorderTraversal(TreeNode root) {
        // write your code here
        
        ArrayList<Character> result = new ArrayList<>();
        travelsal(root, result);
        for(char c : result) {
        	System.out.print(c + " ");
        }
    }
    
    //把root为根的preorder加入result里面
    public static void travelsal(TreeNode root, ArrayList<Character> result)
    {
        if(root == null)
        {
            return;
        }
        
        result.add(root.value);
        travelsal(root.left, result);
        travelsal(root.right, result);
    }
}
