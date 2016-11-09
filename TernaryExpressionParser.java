package com.leetcode.paid;

import java.util.*;

public class TernaryExpressionParser {
	//三元表达式解析器
	
	/*Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. 
	You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F 
	(T and F represent True and False respectively).

	Note:
	
	The length of the given string is ≤ 10000.
	Each number will contain only one digit.
	The conditional expressions group right-to-left (as usual in most languages).
	The condition will always be either T or F. That is, the condition will never be a digit.
	The result of the expression will always evaluate to either a digit 0-9, T or F.
	 
	
	Example 1:
	
	Input: "T?2:3"
	
	Output: "2"
	
	Explanation: If true, then result is 2; otherwise result is 3.
	 
	
	Example 2:
	
	Input: "F?1:T?4:5"
	
	Output: "4"
	
	Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
	
	             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
	          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
	          -> "4"                                    -> "4"
	 
	
	Example 3:
	
	Input: "T?T?F:5:3"
	
	Output: "F"
	
	Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
	
	             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
	          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
	          -> "F"                                    -> "F"*/
	
	/*这道题让我们解析一个三元表达式，我们通过分析题目中的例子可以知道，如果有多个三元表达式嵌套的情况出现，
	那么我们的做法是从右边开始找到第一个问号，然后先处理这个三元表达式，然后再一步一步向左推，这也符合程序是从右向左执行的特点。
	那么我最先想到的方法是用用一个stack来记录所有问号的位置，然后根据此问号的位置，取出当前的三元表达式，调用一个eval函数来分析得到结果，
	能这样做的原因是题目中限定了三元表达式每一部分只有一个字符，而且需要分析的三元表达式是合法的，然后我们把分析后的结果和前后两段拼接成
	一个新的字符串，继续处理之前一个问号，这样当所有问号处理完成后，所剩的一个字符就是答案*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "T?2:3"; //2
		String s2 = "F?1:T?4:5"; //4
		String s3 = "T?T?F:5:3"; //F
		
		System.out.println(parseTernaryStack(s1));
		System.out.println(parseTernaryStack(s2));
		System.out.println(parseTernaryStack(s3));
		
		System.out.println();
		
		System.out.println(parseTernary(s1));
		System.out.println(parseTernary(s2));
		System.out.println(parseTernary(s3));
	}
	
	private static String parseTernaryStack(String expression) { //思路是顶上的那个描述, 用到了栈, 次优, 面试先说这个带栈的
		String res = expression;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < expression.length(); i++) { //扫一遍
			if(expression.charAt(i) == '?') {
				stack.push(i); //放入?的下标
			}
		}
		
		while(!stack.isEmpty()) { //从右向左取出问号的位置
			int t = stack.pop();
			res = res.substring(0, t - 1) + eval(res.substring(t - 1, t + 4)) + res.substring(t + 4); //拼接
		}
		
		return res;
	}
	
	private static String eval(String str) {
		if(str.length() != 5) { //这个判断不太确定用处
			return "";
		}
		return str.charAt(0) == 'T' ? str.substring(2, 3) : str.substring(4, 5); //化简
	}
	
	/*下面这种方法更加简洁，没有用到栈，但是用到了STL的内置函数find_last_of，用于查找字符串中最后一个目前字符串出现的位置，
	这里我们找最后一个问号出现的位置，刚好就是最右边的问号，我们进行跟解法一类似的处理，拼接字符串，循环处理*/
	
	private static String parseTernary(String expression) { //思路就是上面的描述, 没有用到栈, 最优
		String res = expression;
		while(res.length() > 1) { //长度比1大的时候
			int i = res.lastIndexOf("?");
			res = res.substring(0, i - 1) + (res.substring(i - 1, i).equals("T") ? res.substring(i + 1, i + 2) : res.substring(i + 3, i + 4)) + res.substring(i + 4);
			//把中间的化简然后拼接
		}
		return res;
	}

}
