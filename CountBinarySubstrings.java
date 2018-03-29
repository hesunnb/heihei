/*Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and 
all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

Notice that some of these substrings repeat and are counted the number of times they occur.

Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
Example 2:
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
Note:

s.length will be between 1 and 50,000.
s will only consist of "0" or "1" characters.*/

class Solution {
    
    //testCase: 00110011, 10101, 100110, 1
    public int countBinarySubstrings(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int prevLength = 0, curLength = 1, result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                curLength++; //相等统计现在的长度
            }
            else {
                prevLength = curLength; //不等的话, 把现在的长度给之前的长度
                curLength = 1; //现在长度重赋值为1
            }
            if (prevLength >= curLength) { //>是用来得到001,110这种临近地方用的, =是用在1100,0011,01,10这种地方用的
                result++;
            }
        }
        return result; //单独的1或者0直接返回0了
    }
}
