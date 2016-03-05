public class Solution {
    /**
     * @param s a string
     * @return an integer
     */
     
    //动规，下面代码最多两重循环，所以复杂度O(n2)
    public int minCut(String s) {
        // write your code here
        if(s == null || s.length() == 0)
        {
            return 0;
        }
        
        boolean[][] isPalindrome = getIsPalindrome(s); //在主函数中得到这个表
        
        int[] f = new int[s.length() + 1]; //f[i]表示前i个字符，所以ｆ的最后一个值表示整个回文串，所以f要大一格
        for(int i = 0; i < f.length; i++)
        {
            f[i] = i - 1; //前ｉ个字母最坏的情况要切ｉ－１刀，所以先把最坏情况赋值
        }
        
        for(int i = 1; i <= s.length(); i++) //现在这个循环就是找能切的最小的次数，注意ｉ会到达s.length()的位置
        {
            for(int j = 0; j < i; j++) //i跑的是ｆ的下标，ｊ跑的是字符串本身的下标
            {
                if(isPalindrome[j][i - 1]) //每次在ｊ坐标（前面）切，如果ｊ到新加进来的那个字母的地方有回文串就进入（有两种情况：１是在ｊ前方较远处就找到了回文串；２是直到ｊ自己了，就是自己找到自己了，才是回文串；　所以无论是哪种情况，都会进入以下的语句；重点在于ｆ[j]会保留从头到ｊ的最小切的次数，所以无论这两种情况找到回文的时候都会加上前面的ｆ[j]，同时在加上切后面回文的这刀）
                {
                    f[i] = Math.min(f[i], f[j] + 1); //(括号中的f[i]是原来的最坏情况)
                }
            }
        }
        return f[s.length()]; //f最后的值就是最小的切数
    }
    
    private boolean[][] getIsPalindrome(String s) //得到一个字符串的回文表，可用来判断s中的回文串个数
    {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++)
        {
            isPalindrome[i][i] = true; //把步长为０的初始化
        }
        
        for(int i = 0; i < s.length() - 1; i++) //把步长位１的初始化，注意i要到s.length() - 1的地方，否则越界啦
        {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        
        for(int length = 2; length < s.length(); length++) //把步长为２及以上的初始化
        {
            for(int start = 0; start + length < s.length(); start++)
            {
                isPalindrome[start][start + length] = isPalindrome[start + 1][start + length - 1] && (s.charAt(start) == s.charAt(start + length)); //比如求０到５的串是不是回文，要看１到４之间是不是回文（１到４的步长为３，所以在之前的循环中一定已经求出了结果），同时还要满足头尾元素相等
            }
        }
            return isPalindrome;
    }
};