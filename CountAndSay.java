/*
The count-and-say sequence is the sequence of integers beginning as follows:

1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.

11 is read off as "two 1s" or 21.

21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Notice
The sequence of integers will be represented as a string.

Example

Given n = 5, return "111221".
*/

public class Solution {
    public String countAndSay(int n) {
        
        if(n <= 0) { //小于等于0不成立
            return null;
        }
        
        String oldString = "1";
        while(n > 1) { //n = 1的时候不执行, n = 2的时候执行一遍(得到11), n = 3的时候执行两遍(得到21)...
            StringBuilder sb = new StringBuilder();
            char[] oldChars = oldString.toCharArray();
            for(int i = 0; i < oldChars.length; i++) { //找重复的数字
                int count = 1;
                while((i + 1) < oldChars.length && oldChars[i] == oldChars[i + 1]) { //下标i的下一个有值并且和i处的相等
                    count++;
                    i++; //注意次数i也++, 最后加入sb中的是重复元素的最后一个(尽管值都一样)
                }
                sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
            }
            oldString = sb.toString();
            n--;
        }
        return oldString; //n = 1, while不执行的时候直接返回1
    }
}
