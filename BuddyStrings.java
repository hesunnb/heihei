/*Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.*/

class Solution {

    //想的方法和答案一样, 就是感觉不够简洁, 但也没有更简洁的方法了, 看一眼就行
    public boolean buddyStrings(String A, String B) {
        if(A == null || B == null || A.length() != B.length()) {
            return false;
        }
        
        int num = 0;
        int index1 = -1;
        int index2 = -1;
        if(A.equals(B)) { //如果A和B相等, 那么只有A或B中自己有重复字母即可, 重复字母用于交换
            Set<Character> set = new HashSet<>();
            for(char c : A.toCharArray()) {
                if(!set.add(c)) { //set加入重复字母返回false
                    return true;
                }
            }
            return false;
        }
        for(int i = 0; i < A.length(); i++) { //如果A与B不相等
            if(A.charAt(i) != B.charAt(i)) {
                if(index1 == -1) {
                    index1 = i; //记录不相等位置坐标
                } else {
                    index2 = i;
                }
                num++;
            }
        }
        if(num != 2) { //num用来统计不相等字母个数, 按照题意, num只能为2
            return false;
        }
        if((A.charAt(index1) + A.charAt(index2)) != (B.charAt(index2) + B.charAt(index1))) { 
            //不相等位置坐标拼接的字符串如果不相等也返回false
            return false;
        }
        return true;
    }
}
