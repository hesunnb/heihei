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

    //最巧妙的方法: 就看A+A包不包含B, 因为A+A已经包含了A所有的翻转情况
    public boolean rotateString(String A, String B) {
        
        if(A == null || B == null || A.length() != B.length()) {
            return false;
        }
        
        return A.length() == B.length() && (A + A).contains(B);
    }
    
    //leetcode: 三步翻转
    public boolean rotateString(String A, String B) {
        
        if(A == null || B == null || A.length() != B.length()) {
            return false;
        }
        
        if(A.equals(B)) {
            return true;
        }
        
        char[] nums = A.toCharArray();
        for(int i = 0; i < nums.length; i++) {
            reverse(nums, 0, nums.length-2);
            reverse(nums, 0, nums.length-1);
            if(String.valueOf(nums).equals(B)) {
                return true;
            }
        }
        return false;
    }
    
    public void reverse(char[] nums, int start, int end) { //传过来的是地址, 集合和数组都可以直接交换, 值就不可以啦
    
        for(int i = start, j = end; i < j; i++, j--) { //翻转的具体实现
            char temp = nums[i]; //就是交换两个数
            nums[i] = nums[j]; //交换
            nums[j] = temp; //交换
        }
    }
    
    
    //lintcode: 复杂度O(n):三步翻转法: 记RotateArray的写法
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
