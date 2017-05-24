public class Solution {
    /**
     * @param n the integer to be reversed
     * @return the reversed integer
     */
    public int reverseInteger(int n) {
        // Write your code here
        
        int rst = 0;
        
        while(n != 0) 
        {
            int next_rst = rst * 10 + n % 10; //把尾数提取出来，存下
            n = n / 10; //把尾数撇掉
            if(next_rst/10 != rst) //这句就是看看有没有越界发生，发生啦截取后的值除以10以后肯定和原来的不一样
            //int的值在计算的情况下，结果大于intmax会截取比如int n = 123456789 * 123456789，会截取；但是如果直接int n = 123456789999L, 
	    //就是把long给int，是不可以哒
            {
                rst  = 0;
                break;
            }
            rst = next_rst; //没有越界的情况下给结果
        }
        return rst;
        
        
	/*String result = "";
	String temp = String.valueOf(n);
	Stack<Character> st = new Stack<Character>();

	for(int i = 0; i < temp.length(); i++)
	{
		if(temp.charAt(i) != '-')
		{
			st.push(temp.charAt(i));
		}
	}
	if(n < 0)
	{
		result += "-";
	}
	while(!st.isEmpty())
	{
		result += st.pop();
	}
	long val = Long.parseLong(result);
	if(val > Integer.MAX_VALUE || val < Integer.MIN_VALUE)
	{
	    return 0;
	}
	else
	{
	    return Integer.parseInt(result);
	}*/
    }
}
