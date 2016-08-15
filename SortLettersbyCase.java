/*
Given a string which contains only letters. Sort it by lower case first and upper case second.
Notice

It's NOT necessary to keep the original order of lower-case letters and upper case letters.

 Example

For "abAcD", a reasonable answer is "acbAD"
*/

public class Solution {
    /** 
     *@param chars: The letter array you should sort by Case
     *@return: void
     */
    public void sortLetters(char[] chars) {
        //write your code here
        
        if(chars == null || chars.length == 0) {
            return;
        }
        
        char pivot = 'Z';
        int store = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > pivot) { //跟快排反过来, 把大的排在前, 小的排在后; store与i中间卡的都是小于等于Z的
                swap(chars, i, store);
                store++;
            }
        }
        //这个地方也不用swap
    }
    
    private static void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
