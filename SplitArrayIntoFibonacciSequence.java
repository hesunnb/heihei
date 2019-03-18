/*Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the 
number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
Example 2:

Input: "11235813"
Output: [1,1,2,3,5,8,13]
Example 3:

Input: "112358130"
Output: []
Explanation: The task is impossible.
Example 4:

Input: "0123"
Output: []
Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
Example 5:

Input: "1101111"
Output: [110, 1, 111]
Explanation: The output [11, 0, 11, 11] would also be accepted.
Note:

1 <= S.length <= 200
S contains only digits.*/

class Solution {
    
    //标准的递归过程
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        if(S == null || S.length() == 0) {
            return result;
        }
        
        helper(S, result, 0);
        return result;
    }
    
    public boolean helper(String S, List<Integer> result, int index) {
        if(index == S.length() && result.size() >= 3) { //因为Fibonacci数列有效添加进入的数字可以多于3个, 所以要>=3
            return true;
        }
        for(int i = index; i < S.length(); i++) {
            if(S.charAt(index) == '0' && i > index) { //如果是以0开头, 那么就只能切出'0', 0以后的就不能切了
                break;
            }
            long num = Long.parseLong(S.substring(index, i + 1));
            if(num > Integer.MAX_VALUE) {
                break;
            }
            if(result.size() >= 2 && num > result.get(result.size() - 1) + result.get(result.size() - 2)) { //此处一定要用 num > 不能
                break; //用num !=, 比如"123456579", [123,456,579], 当走到123, 456, 子串切到5的时候, 5不大于123+456, 所以还会继续循环
            } //切出57, 最后到579满足条件; 如果要是用!=, 那么5!=123+456, 那么直接break了, 579这个有效数字就没有切到, 就错了
            if(result.size() < 2 || num == result.get(result.size() - 1) + result.get(result.size() - 2)) {
                result.add((int)num);
                if(helper(S, result, i + 1)) {
                    return true;
                }
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
}
