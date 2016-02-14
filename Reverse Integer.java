public class Solution {
    /**
     * @param n the integer to be reversed
     * @return the reversed integer
     */
    public int reverseInteger(int n) {
        // Write your code here
        
		String result = "";
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
		}
    }
}
