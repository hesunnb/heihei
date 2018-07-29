/*Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".*/

class Solution {
    
    //solution1: 双栈解法就搞定这题了, 以后遇到里面扩展这种题都可以考虑双栈; testCase就例子中的这三个就非常好
    public String decodeString(String s) {
        
        if(s == null || s.length() == 0) {
            return "";
        }

        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) { //比如13[a]2[b]这种, 统计大于个位的数字
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') { //""的长度是0, "".append("a")就是"a"本身
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop()); //取之前的子串
                int repeatTimes = countStack.pop(); //取重复次数
                for (int i = 0; i < repeatTimes; i++) { //按重复次数生成子串
                    temp.append(res);
                }
                res = temp.toString(); //把拼好的子串给结果
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
    
    
    //solution2: 递归解法
    private int pos = 0;
    public String decodeString(String s) {
        
        if(s == null || s.length() == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        String num = "";
        for (int i = pos; i < s.length(); i++) {
            if (s.charAt(i) != '[' && s.charAt(i) != ']' && !Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            } else if (Character.isDigit(s.charAt(i))) {
                num += s.charAt(i);
            } else if (s.charAt(i) == '[') {
                pos = i + 1;
                String next = decodeString(s);
                for (int n = Integer.valueOf(num); n > 0; n--) sb.append(next);
                num = "";
                i = pos;
            } else if (s.charAt(i) == ']') {
                pos = i;
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
