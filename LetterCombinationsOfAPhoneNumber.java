/*Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/

public class Solution {
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<String>();
        if(digits == null || digits.length() == 0) {
            return result;
        }
        
        String[] numbers = {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz","*"," ","#"}; //把电话板先自己定义到数组里
        letterHelper(result, new StringBuilder(), numbers, 0, 0, digits);
        return result;
    }
    
    private static void letterHelper(List<String> result, StringBuilder sb, String[] numbers, int pos, int count, String digits)
    {
    	if(sb.length() == digits.length()) { //如果长度和digits相等了就说明是想要的结果
    		result.add((new StringBuilder(sb)).toString()); //new一个StringBuilder再加入
    	}
          
        if(count >= digits.length()) { //count用于记录访问到digits中的第几位了, 大于等于digits.length()说明到了digits末尾,
        //就返回
        	return;
        }
        
        String temp = numbers[digits.charAt(count) - '0' - 1]; //把digits中的数对应到numbers里面的字符串
        for (int i = pos; i < temp.length(); i++) {
            sb.append(temp.charAt(i)); //加入字母, 注意是i
            letterHelper(result, sb, numbers, pos, count + 1, digits); //要给pos, 每一个字符串都得从头开始, count要+1
            sb.delete(sb.length() - 1, sb.length());//把最后一个数删掉换成下一个字母
        }
    }
}
