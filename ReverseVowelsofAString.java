/*Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".*/

class Solution {
    //own, 两点指针即可, 头部与尾部元音对换, 接下来依次向前
    public String reverseVowels(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('O');
        list.add('U');
        
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0,j = sb.length() - 1; i < j;) {
            if(!list.contains(sb.charAt(i))) {
                i++;
            }
            if(!list.contains(sb.charAt(j))) {
                j--;
            }
            if(list.contains(sb.charAt(i)) && list.contains(sb.charAt(j))) {
                char temp = sb.charAt(i);
                sb.replace(i,i+1,String.valueOf(sb.charAt(j))); //替换
                sb.replace(j,j+1,String.valueOf(temp));
                i++;
                j--;
            }
        }
        return sb.toString();
    }
}
