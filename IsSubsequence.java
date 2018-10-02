/*Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, 
and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. 
In this scenario, how would you change your code?*/

class Solution {
  
    //version1: O(s.length() + t.length())时间
    public boolean isSubsequence(String s, String t) {
        
        if(s == null || t == null) {
            return false;
        }
        
        int i = 0, j = 0;
        while(i < t.length() && j < s.length()) {
            if(t.charAt(i) == s.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == s.length();
    }
  
    //version for Follow up:
    /*// Follow-up: O(N) time for pre-processing, O(Mlog?) for each S. //M是每一个S的长度, ?是每个list的长度
    // Eg-1. s="abc", t="bahbgdca"
    // idx=[a={1,7}, b={0,3}, c={6}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=3
    //  i=2 ('c'): prev=6 (return true)
    // Eg-2. s="abc", t="bahgdcb"
    // idx=[a={1}, b={0,6}, c={5}]
    //  i=0 ('a'): prev=1
    //  i=1 ('b'): prev=6
    // i=2 ('c'): prev=? (return false)*/
    public boolean isSubsequence(String s, String t) {
        if(s == null || t == null) {
            return  false;
        }
        
        List<Integer>[] idx = new List[26]; // 修改版可以少用空间, 用26个就行
        for (int i = 0; i < t.length(); i++) {
            if (idx[t.charAt(i) - 'a'] == null) {
                idx[t.charAt(i) - 'a'] = new ArrayList<>();
            }
            idx[t.charAt(i) - 'a'].add(i);
        }
        
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            if (idx[s.charAt(i) - 'a'] == null) {
                return false; // Note: char of S does NOT exist in T causing NPE
            }
            int j = Collections.binarySearch(idx[s.charAt(i) - 'a'], prev); //每次对于S的第一个值都搜0, 如果正好0存在那么因为0肯定在最前面
            //所以返回的是0, 如果不是0, 比0大的第一个数也是在0的位置, 所以返回-1(java库对binarySearch方法的定义)
            if (j < 0) { //-1也会使j变为0, 所以这样就使得第一次取的字母一定是某个list里面的第一个值
                j = -j - 1;
            }
            if (j == idx[s.charAt(i) - 'a'].size()) { //当prev过大而在list中不存在, 那么返回的值是list.size()+1, 因为java库的binarySearch
                //方法的起始index是从1开始的, 所以j-1正好和list.size()相等, 那么就返回false结束了
                return false;
            }
            prev = idx[s.charAt(i) - 'a'].get(j) + 1; //在取到的index的结果的基础上+1, 保证后面找的index都会比之前的要大, 这样就保证了sequence
            //的顺序
        }
        return true;
    }
    /*对prev的值和binarySearch的解释: The prev variable is an index where previous character was picked from the sequence. And for the next 
    character to be picked, you have to select it only after this index is the string 'T'.

    For instance, if S = "abcd" and T = "abdced".
    The index list mapping looks like,

    a -> 0
    b -> 1
    c -> 3
    d -> 2,5
    e -> 4
    After you pick a, and b, c will be picked, and index is 3. Now if you have to pick d, you can't pick index 2 because c was picked at 3, 
    so you have to binary search for index which comes after 3. So it returns 5.*/
}
