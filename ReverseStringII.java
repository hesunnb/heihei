/*Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the 
string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k 
characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]*/

class Solution {
    
    //solution1: 没用StringBuilder
    public String reverseStr(String s, int k) {
        
        if(s == null || s.length() == 0 || k <= 0) {
            return s;
        }
        
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1, n - 1); //取j为交换的尾处
            swap(arr, i, j); //交换i,j之间的元素
            i += 2 * k; //i往后走
        }
        return String.valueOf(arr);
    }
    
    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
    
    //StringBuilder(own)
    public String reverseStr(String s, int k) {
        if(s == null || s.length() == 0) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(i + 2*k-1 < s.length()) {
            StringBuilder sb = new StringBuilder(s.substring(i, i + k));
            result.append(sb.reverse());
            result.append(s.substring(i+k, i+2*k));
            i += 2*k;
        }
        if(i + k - 1 < s.length()) {
            StringBuilder sb = new StringBuilder(s.substring(i, i + k));
            result.append(sb.reverse());
            result.append(s.substring(i + k));
        } else if(i + k - 1 >= s.length()) {
            StringBuilder sb = new StringBuilder(s.substring(i));
            result.append(sb.reverse());
        }
        return result.toString();
    }
    
    //solution2: 用了StringBuilder
    public String reverseStr(String s, int k) {
        
        if(s == null || s.length() == 0 || k <= 0) {
            return s;
        }
        
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < s.length(); i += 2*k) {
            StringBuilder sb = new StringBuilder();
            int j = i;
            for(j = i; j < i + k && j < s.length(); j++) { //j < s.length()在这里针对"a",2这种
                sb.append(s.charAt(j));
            }
            sb.reverse();
            for(; j < i + 2*k && j < s.length(); j++) {
                sb.append(s.charAt(j));
            }
            result.append(sb);
        }
        return result.toString();
    }
}
