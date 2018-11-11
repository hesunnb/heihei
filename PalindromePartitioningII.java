/*Given a string s, cut s into some substrings such that every substring is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example
Given s = "aab",

Return 1 since the palindrome partitioning ["aa", "b"] could be produced using 1 cut.*/

public class Solution {
     
    //用动归解O(n^2)时间, O(n^2)空间
    public int minCut(String s) {
        // write your code here
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        boolean[][] isPalindrome = getIsPalindrome(s); //在主函数中得到这个表
        
        int[] f = new int[s.length() + 1]; //f[i]表示前i个字符，所以ｆ的最后一个值表示整个回文串，所以f要大一格
        for(int i = 0; i < f.length; i++) {
            f[i] = i - 1; //前i个字母最坏的情况要切i - 1刀，所以先把最坏情况赋值
        }
        
        for(int i = 1; i <= s.length(); i++) { //现在这个循环就是找能切的最小的次数，注意ｉ会到达s.length()的位置
            for(int j = 0; j < i; j++) { //i跑的是ｆ的下标，ｊ跑的是字符串本身的下标
                if(isPalindrome[j][i - 1]) { //如果isPalindrome[j][i - 1]为真, 表示从j到i - 1截取的字符串是回文串,
                //是回文串就在回文串前面切一刀(每次在j坐标(前面)切, 就是 + 1), 分离出该回文串, 把结果放在f[i]位置, 因为是头i个字符会加这么一刀, 
                //f[j]会保留从头到j的最小切的次数，同时再加上切后面回文的这刀
                
                    f[i] = Math.min(f[i], f[j] + 1); //(括号中的f[i]是原来的最坏情况)
                    //f[i]中存的就是头i个字母需要切几刀, 比如f[3] = 1, 头3个字母需要切一刀
                }
            }
        }
        return f[s.length()]; //f最后的值就是最小的切数
    }
    
    //区间型动归, 按照区间长度进行动归
    private boolean[][] getIsPalindrome(String s) { //得到一个字符串的回文表，可用来判断s中的回文串个数
        boolean[][] isPalindrome = new boolean[s.length()][s.length()]; 
        for(int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true; //把步长为0的初始化
        }
        
        for(int i = 0; i < s.length() - 1; i++) { //把步长位1的初始化，注意i要到s.length() - 1的地方，否则越界啦
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        
        for(int length = 2; length < s.length(); length++) { //把步长为２及以上的初始化
            for(int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length] = isPalindrome[start + 1][start + length - 1] && 
                    (s.charAt(start) == s.charAt(start + length)); 
                //比如求０到５的串是不是回文, 要看１到４之间是不是回文(１到４的步长为３, 所以在之前的循环中一定已经求出了结果), 
                //同时还要满足头尾元素相等
            }
        }
        return isPalindrome;
    }
    
    /*testCase: "abba", "abbcca"
      a b b a
    a 1 0 0 1
    b   1 1 0
    b     1 0
    a       1
    0 1 2 3 4
   -1 0 1 1 0
    
      a b b c c a
    a 1 0 0 0 0 0
    b   1 1 0 0 0
    b     1 0 0 0
    c       1 1 0
    c         1 0
    a           1
    0 1 2 3 4 5 6
   -1 0 1 1 2 2 3
    */
};
