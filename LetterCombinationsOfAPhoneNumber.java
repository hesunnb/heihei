/*Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].*/

public class Solution {
    
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return result;
        }
        
        String[] numbers = new String[] {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        helper(result, new StringBuilder(), numbers, digits, 0);
        return result;
    }
    
    public void helper(List<String> result, StringBuilder sb, String[] numbers, String digits, int pos) {
        if(sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        
        int index = digits.charAt(pos) - '0' - 1;
        for(int i = 0; i < numbers[index].length(); i++) {
            sb.append(numbers[index].charAt(i));
            helper(result, sb, numbers, digits, pos + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    //递归查找, 复杂度就是每个数字对应的字母盘的字母个数相乘, 比如"abc","def",就是O(3*3)
    public List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<String>();
        if(digits == null || digits.length() == 0) {
            return result;
        }
        
        String[] numbers = {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz","*"," ","#"}; //把电话板先自己定义到数组里
        letterHelper(result, new StringBuilder(), numbers, 0, digits);
        return result;
    }
    
    private static void letterHelper(List<String> result, StringBuilder sb, String[] numbers, int count, String digits) {
    	if(sb.length() == digits.length()) { //如果长度和digits相等了就说明是想要的结果
            result.add((new StringBuilder(sb)).toString()); //new一个StringBuilder再加入
            return;
    	}
        
        String temp = numbers[digits.charAt(count) - '0' - 1]; //把digits中的数对应到numbers里面的字符串
        for (int i = 0; i < temp.length(); i++) {
            sb.append(temp.charAt(i)); //加入字母, 注意是i
            letterHelper(result, sb, numbers, count + 1, digits); //每一个字符串都得从头开始, count要+1
            sb.deleteCharAt(sb.length() - 1);//把最后一个数删掉换成下一个字母
        }
    }
}
