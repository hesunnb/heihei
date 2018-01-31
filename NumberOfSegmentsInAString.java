/*Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5*/


class Solution {

    //solution2:(own)用split肯定不行, 因为有多个空格连着的情况
    public int countSegments(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != ' ') {
                if(i == s.length() - 1 || s.charAt(i + 1) == ' ') { //如果最后一个是' '那么result+1; 如果i走到最后都没有遇到空格, 
                //说明就是一整个单词, result也+1
                    result += 1;
                }
            }
        }
        return result;
    }
}
