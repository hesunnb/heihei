/*Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example
Given "25525511135", return

[
  "255.255.11.135",
  "255.255.111.35"
]
Order does not matter.*/

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        
        //0.0.0.0是合法ip, 第一个0开头是可以的
        List<String> result = new ArrayList<String>();
        if(s == null || s.length() < 4 || s.length() > 12) { //不符合ip标准
            return result;
        }
        
        for(int i = 1; i <= 3; i++) { // first cut
            if(s.length() - i > 9) { //如果i后面的字母个数超过9个, 肯定有的小段会超过3个, 所以就不用再往后看了
                continue;
            }
            for(int j = i + 1; j <= i + 3; j++) { //second cut
                if(s.length() - j > 6) { //同理, j后面不能超过6个
                    continue;
                }
                for(int k = j + 1; k <= j + 3 && k < s.length(); k++) { // third cut, 此处一定要加k < s.length(), k只有小于s.length(), 最后的"."才会有值, 否则k要是等于s.length(), s.substring(k)就会返回"", parseInt就报错了
                    if(s.length() - k > 3) { //k后面不能超过3个
                        continue;
                    }
                    // the four int's seperated by "."
                    int a = Integer.parseInt(s.substring(0, i)); // notice that "01" can be parsed into 1. Need to deal with that later.
                    int b = Integer.parseInt(s.substring(i, j));
                    int c = Integer.parseInt(s.substring(j, k));
                    int d = Integer.parseInt(s.substring(k));
                    if(a > 255 || b > 255 || c > 255 || d > 255) {
                        continue;
                    }
                    String ip = a + "." + b + "." + c + "."+ d;
                    if(ip.length() - 3 < s.length()) { // this is to reject those int's parsed from "01" or "00"-like substrings
                        //-3是那3个"."的长度
                        continue;
                    }
                    result.add(ip);
                }
            }
        }
        return result;
    }
}
/*testCase:
	Input: "010010"
	Output: ["0.1.0.10","0.1.0.10","0.1.1.0","0.10.0.10","0.10.1.0","0.100.1.0","1.0.0.10","1.0.1.0","1.0.1.0","10.0.1.0"] //没有处理0的时候
	Expected: ["0.10.0.10","0.100.1.0"] //处理之后
*/
