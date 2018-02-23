/*You are given a license key represented as a string S which consists only alphanumeric character and dashes. 
The string is separated into N+1 groups by N dashes.

Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for 
the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must 
be a dash inserted between two groups and all lowercase letters should be converted to uppercase.

Given a non-empty string S and a number K, format the string according to the rules described above.

Example 1:
Input: S = "5F3Z-2e-9-w", K = 4

Output: "5F3Z-2E9W"

Explanation: The string S has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.
Example 2:
Input: S = "2-5g-3-J", K = 2

Output: "2-5G-3J"

Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could 
be shorter as mentioned above.
Note:
The length of string S will not exceed 12,000, and K is a positive integer.
String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
String S is non-empty.*/


class Solution {

    //solution2:(own)
    public String licenseKeyFormatting(String S, int K) {
        
        if(S == null || S.length() == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++) { //把'-'都去掉放到StringBuilder中
            char c = S.charAt(i);
            if(c == '-') continue;
            if(Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
            }
            sb.append(c);
        }

        int pos = sb.length() % K; //计算最开始插入的地方
        if(pos != 0 && pos < sb.length()) { //对于"2",2这种例子, pos要比字符串长度小才行, 要不会得到"2-"
        	sb.insert(pos, '-');
        	pos += 1; //加1是把刚加入的'-'位置空过去
        }
        while(pos < sb.length()) {
        	System.out.println(pos);
        	pos += K;
        	if(pos >= sb.length()) { //如果加K之后已经到了字符串尾, 就不再加'-'了
        		break;
        	}
        	sb.insert(pos, '-');
        	pos += 1; //把刚加入的'-'位置空过去
        }
        
        return sb.toString();
    }
}
