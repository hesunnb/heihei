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
    
    //version1: IsSubsequence的Follow up题目
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
    
    
    //version2: 也是一种处理方式, 见注释
    public int numMatchingSubseq(String S, String[] words) {

        if(S == null || words == null || words.length == 0) {
            return 0;
        }
        List<Integer[]>[] waiting = new List[128];
        
        for (int c = 0; c <= 'z'; c++) {
            waiting[c] = new ArrayList();
        }
            
        for (int i = 0; i < words.length; i++) {
            waiting[words[i].charAt(0)].add(new Integer[]{i, 1});
        }
            
        for (char c : S.toCharArray()) {
            List<Integer[]> advance = new ArrayList(waiting[c]);
            waiting[c].clear();
            for (Integer[] a : advance) {
                waiting[a[1] < words[a[0]].length() ? words[a[0]].charAt(a[1]++) : 0].add(a);
            }
        }
        return waiting[0].size();
    }
    /*Explanation:
    I go through S once, and while I'm doing that, I move through all words accordingly. That is, I keep track of how much of each word I've 
    already seen, and with each letter of S, I advance the words waiting for that letter. To quickly find the words waiting for a certain 
    letter, I store each word (and its progress) in a list of words waiting for that letter. Then for each of the lucky words whose current 
    letter just occurred in S, I update their progress and store them in the list for their next letter.

    Let's go through the given example:

    S = "abcde"
    words = ["a", "bb", "acd", "ace"]
    I store that "a", "acd" and "ace" are waiting for an 'a' and "bb" is waiting for a 'b' (using parentheses to show how far I am in each 
    word):

    'a':  ["(a)", "(a)cd", "(a)ce"]
    'b':  ["(b)b"]
    Then I go through S. First I see 'a', so I take the list of words waiting for 'a' and store them as waiting under their next letter:

    'b':  ["(b)b"]
    'c':  ["a(c)d", "a(c)e"]
    None: ["a"]
    You see "a" is already waiting for nothing anymore, while "acd" and "ace" are now waiting for 'c'. Next I see 'b' and update accordingly:

    'b':  ["b(b)"]
    'c':  ["a(c)d", "a(c)e"]
    None: ["a"]
    Then 'c':

    'b':  ["b(b)"]
    'd':  ["ac(d)"]
    'e':  ["ac(e)"]
    None: ["a"]
    Then 'd':

    'b':  ["b(b)"]
    'e':  ["ac(e)"]
    None: ["a", "acd"]
    Then 'e':

    'b':  ["b(b)"]
    None: ["a", "acd", "ace"]
    And now I just return how many words aren't waiting for anything anymore.*/
}
