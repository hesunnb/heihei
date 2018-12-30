/*We are given two strings, A and B.

A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', 
then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.

Example 1:
Input: A = 'abcde', B = 'cdeab'
Output: true

Example 2:
Input: A = 'abcde', B = 'abced'
Output: false

Note:

    A and B will have length at most 100.

*/

public class Solution {

    //复杂度O(n):三步翻转法
    public void rotateString(char[] str, int offset) {
        // write your code here
        
        if(str == null || str.length == 0) {
            return;
        }
        
        int len = str.length;
        int offsetfinal = offset % len; //offset可能很大,像10000这种，所以求余数组长判断移位数
        if(offsetfinal != 0) { //当移位不是0就移动，否则就不移动（移动0位或者len个位都不移动）
            int index = len - offsetfinal; //index指向要翻转的地方
            if(index <= len - 1) { //以防万一，保证index不越界
                rotate(str, index, len - 1); //三步翻转法，index到尾
                rotate(str, 0, index - 1); //头到index
                rotate(str, 0, len - 1); //整体翻转
            }
        }
    }
    
    public void rotate(char[] str, int start, int end) { //翻转具体实现，和recover rotated sorted array的这个函数一样
        for(int i = start, j = end; i < j; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }
}
