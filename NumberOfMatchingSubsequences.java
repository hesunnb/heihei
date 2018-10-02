/*Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].*/

class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        
        int result = 0;
        if(S == null || words == null || words.length == 0) {
            return result;
        }
        
        List<Integer>[] idx = new List[26]; // 修改版可以少用空间, 用26个就行
        for (int i = 0; i < S.length(); i++) {
            if (idx[S.charAt(i) - 'a'] == null) {
                idx[S.charAt(i) - 'a'] = new ArrayList<>();
            }
            idx[S.charAt(i) - 'a'].add(i);
        }
        
        for(String s : words) {
            if(isSubsequence(s, idx)) {
                result++;
            }
        }
        return result;
    }
    
    public boolean isSubsequence(String s, List<Integer>[] idx) {
        
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
}
