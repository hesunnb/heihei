/*The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern 
in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I*/

class Solution {

    //solution1: 用了O(n)时间, O(n)空间, 但是易于模拟
    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0 || numRows == 1 || numRows > s.length()) {
            return s;
        }
        
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
        	sb[i] = new StringBuilder(); //每一行一个StringBuilder
        }

        int i = 0;
        while (i < s.length()) {
            for (int idx = 0; idx < numRows && i < s.length(); idx++) { // vertically down
                sb[idx].append(s.charAt(i++)); //从上到下, 每一行对应的StringBuilder加入对应的s中的字符
            }
            for (int idx = numRows-2; idx >= 1 && i < s.length(); idx--) { // obliquely up
                sb[idx].append(s.charAt(i++)); //去掉头尾行, 中间行加入对应的字母
            }
        }
        for (int idx = 1; idx < sb.length; idx++) { //统一加到第一个StringBuilder里面
            sb[0].append(sb[idx]);
        }
        return sb[0].toString();
    }
    
    
    //solution2: (own)
    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0 || numRows == 1 || numRows > s.length()) {
            return s;
        }
        
        int step = numRows * 2 - 2; //根据找规律, 能够发现第一位跳的最长就是总步数
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            int step1 = step - 2 * i; //比如有7行, 那么就是+12,+0; +10,+2; +8,+4; +6,+6; +4,+8; +2,+10; +0,+12
            int step2 = step - step1; //step1就是前面的步数, step2就是后面的步数
            int flag = 0;
            for(int j = i; j < s.length();) { //j从i开始, 从每一行开始找, 找numRows行就ok了
                if(flag == 0) { //这里用了flag, 可能不太尽如人意
                    if(step1 != 0) { //等于0的时候不能append值, 否则就加重复了
                        sb.append(s.charAt(j));
                    }
                    j += step1; //j跳前面的步数
                    flag = 1;
                } else if(flag == 1) {
                    if(step2 != 0) {
                        sb.append(s.charAt(j));
                    }
                    j += step2; //j跳后面的步数
                    flag = 0;
                }
            }
        }
        return sb.toString();
    }
}
